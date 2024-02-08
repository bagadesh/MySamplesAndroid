@file:OptIn(ExperimentalCoroutinesApi::class)

package com.bagadesh.featureflags.library

import com.bagadesh.featureflags.domain.Feature
import com.bagadesh.featureflags.domain.FeatureVariable
import com.bagadesh.featureflags.logger.Logger
import com.bagadesh.featureflags.parser.FeatureFlagParser
import com.bagadesh.featureflags.preference.PreferenceWrapper
import com.bagadesh.featureflags.repositoryImpl.FeatureFlagRepositoryImpl
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

/**
 * Created by bagadesh on 15/04/23.
 */
class FeatureMeteor(
    private val featureFlagParser: FeatureFlagParser,
    private val preference: PreferenceWrapper,
    private val logger: Logger,
    private val gson: Gson,
    private val dispatcher: WrapDispatcher,
) {

    private val featuresFlow = MutableStateFlow<List<Feature>>(emptyList())
    private val featuresMapFlow = featuresFlow.mapLatest { list -> list.associateBy { it.name } }.stateIn(dispatcher.scope, SharingStarted.Eagerly, emptyMap())
    val features: List<Feature>
        get() = featuresFlow.value
    val featuresMap: Map<String, Feature>
        get() = featuresMapFlow.value

    init {
        dispatcher.ioLaunch { init() }
    }

    private suspend fun init() {
        val fromConfigList = featureFlagParser.parser().map {
            val key = it.name
            val value = preference.getBoolean("${FeatureFlagRepositoryImpl.FEATURE_KEY}$key") { it.enabled }
            logger.debug(message = "FeatureFlagRepository ::getFeatures feature override found key=$key, value=$value")
            Feature(
                name = it.name,
                enabled = value,
                variables = it.variables
            )
        }.map { feature ->
            feature.copy(
                variables = feature.variables.map { variable ->
                    val value = preference.getString("${FeatureFlagRepositoryImpl.VARIABLE_KEY}${feature.name}-${variable.name}") { FeatureFlagRepositoryImpl.MY_EMPTY_ONE }
                    if (value != FeatureFlagRepositoryImpl.MY_EMPTY_ONE) {
                        logger.debug(message = "FeatureFlagRepository ::getFeatures feature variable override found feature's name=${feature.name}, variable's name=${variable.name}, variable value = ${variable.value}")
                        val valueFromConfig = variable.value
                        val modifiedValue = gson.fromJson(value, valueFromConfig.javaClass)
                        FeatureVariable(
                            name = variable.name,
                            value = modifiedValue
                        )
                    } else
                        variable
                }
            )
        }
        featuresFlow.emit(fromConfigList)
    }

}