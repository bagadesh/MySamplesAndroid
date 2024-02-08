package com.bagadesh.featureflags.repositoryImpl

import com.bagadesh.featureflags.domain.Feature
import com.bagadesh.featureflags.domain.FeatureVariable
import com.bagadesh.featureflags.domain.ValidateChange
import com.bagadesh.featureflags.logger.Logger
import com.bagadesh.featureflags.parser.FeatureFlagParser
import com.bagadesh.featureflags.preference.PreferenceWrapper
import com.bagadesh.featureflags.repository.FeatureFlagRepository
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Created by bagadesh on 15/04/23.
 */
class FeatureFlagRepositoryImpl @Inject constructor(
    private val preference: dagger.Lazy<PreferenceWrapper>,
    private val featureFlagParser: dagger.Lazy<FeatureFlagParser>,
    private val logger: dagger.Lazy<Logger>,
    private val gson: dagger.Lazy<Gson>,
) : FeatureFlagRepository {

    companion object {
        const val FEATURE_KEY = "feature-key-"
        const val VARIABLE_KEY = "variable-key-"
        const val MY_EMPTY_ONE = "MY_EMPTY_ONE"
    }

    override suspend fun getFeatures(): List<Feature> {
        val fromConfigList = featureFlagParser.get().parser().map {
            val key = it.name
            val value = preference.get().getBoolean("$FEATURE_KEY$key") { it.enabled }
            logger.get().debug(message = "FeatureFlagRepository ::getFeatures feature override found key=$key, value=$value")
            Feature(
                name = it.name,
                enabled = value,
                variables = it.variables
            )
        }.map { feature ->
            feature.copy(
                variables = feature.variables.map { variable ->
                    val value = preference.get().getString("$VARIABLE_KEY${feature.name}-${variable.name}") { MY_EMPTY_ONE }
                    if (value != MY_EMPTY_ONE) {
                        logger.get()
                            .debug(message = "FeatureFlagRepository ::getFeatures feature variable override found feature's name=${feature.name}, variable's name=${variable.name}, variable value = ${variable.value}")
                        val valueFromConfig = variable.value
                        val modifiedValue = gson.get().fromJson(value, valueFromConfig.javaClass)
                        FeatureVariable(
                            name = variable.name,
                            value = modifiedValue
                        )
                    } else
                        variable
                }
            )
        }

        return fromConfigList
    }

    override suspend fun setFeatureEnabled(key: String, enabled: Boolean) {
        preference.get().putBoolean(key = "$FEATURE_KEY$key", value = enabled)
    }

    override suspend fun tryUpdateFeatureVariable(featureKey: String, variableKey: String, defaultValue: Any, changedValue: String): ValidateChange {
        logger.get().debug(message = "FeatureFlagRepository ::tryUpdateFeatureVariable init defaultValue=$defaultValue, changedValue=$changedValue")
        return when (val valueConversion = validateValue(defaultValue, changedValue)) {
            is ValueConversion.Failed -> {
                logger.get().debug(message = "FeatureFlagRepository ::tryUpdateFeatureVariable Failed $valueConversion")
                ValidateChange.Failed
            }

            is ValueConversion.Success -> {
                logger.get().debug(message = "FeatureFlagRepository ::tryUpdateFeatureVariable Success $valueConversion")
                if (defaultValue == valueConversion.value) {
                    logger.get().debug(message = "FeatureFlagRepository ::tryUpdateFeatureVariable ValidateChange.NoDifference")
                    ValidateChange.NoDifference
                } else {
                    logger.get().debug(message = "FeatureFlagRepository ::tryUpdateFeatureVariable changing the variable")
                    preference.get().putString("$VARIABLE_KEY$featureKey-$variableKey", changedValue)
                    ValidateChange.Success(valueConversion.value)
                }
            }
        }
    }

    private fun validateValue(value: Any, changedValue: String): ValueConversion {
        return try {
            logger.get().debug(message = "FeatureFlagRepository ::validateValue changedValue = $changedValue")
            val modifiedValue = gson.get().fromJson(changedValue, value.javaClass)
            logger.get().debug(message = "FeatureFlagRepository ::validateValue modifiedValue = $modifiedValue")
            ValueConversion.Success(value = modifiedValue)
        } catch (exception: Exception) {
            logger.get().error(message = "FeatureFlagRepository ::validateValue ", throwable = exception)
            ValueConversion.Failed(throwable = exception)
        }
    }

    sealed interface ValueConversion {

        data class Failed(val throwable: Throwable) : ValueConversion

        data class Success(val value: Any) : ValueConversion

    }
}