package com.bagadesh.featureflags.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagadesh.featureflags.domain.Feature
import com.bagadesh.featureflags.domain.FeatureFlagUiState
import com.bagadesh.featureflags.domain.ValidateChange
import com.bagadesh.featureflags.logger.Logger
import com.bagadesh.featureflags.repository.FeatureFlagRepository
import com.bagadesh.featureflags.ui.domain.FeatureKeyVariable
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by bagadesh on 14/04/23.
 */
class FeatureFlagViewModel @Inject constructor(
    private val featureFlagRepository: Lazy<FeatureFlagRepository>,
    private val logger: Lazy<Logger>,
) : ViewModel() {


    /**
     * Under no circumstance manipulate the value.
     * Always use Source of truth to update
     */
    var featureFlag = MutableStateFlow<FeatureFlagUiState>(FeatureFlagUiState.Loading)
        private set
    var expandedItems = MutableStateFlow<List<String>>(emptyList())
        private set
    var valueChangedItems = MutableStateFlow<List<FeatureKeyVariable>>(emptyList())
        private set

    init {
        fetchFeatureFlags()
    }

    private fun fetchFeatureFlags() {
        viewModelScope.launch {
            featureFlag.value = FeatureFlagUiState.Success(features = featureFlagRepository.get().getFeatures())
        }
    }

    fun onContentClick(name: String) {
        val result = expandedItems.value.toMutableList()
        if (expandedItems.value.contains(name)) {
            result.remove(name)
        } else {
            result.add(name)
        }
        expandedItems.value = result
    }

    fun setFeatureChecked(key: String, enabled: Boolean) {
        logger.get().debug(message = "FeatureFlagViewModel ::setFeatureChecked key = $key, enabled = $enabled")
        viewModelScope.launch {
            featureFlagRepository.get().setFeatureEnabled(key = key, enabled = enabled)
            fetchFeatureFlags()
        }
    }

    fun saveChangesClick() {
        logger.get().debug(message = "FeatureFlagViewModel ::saveChangesClick started")
        viewModelScope.launch {
            val listOfChangedItems = valueChangedItems.value.toList()
            valueChangedItems.value = emptyList()
            logger.get().debug(message = "FeatureFlagViewModel ::saveChangesClick listOfChangedItems=$listOfChangedItems")
            listOfChangedItems.forEach {
                when (val validateResult = featureFlagRepository.get().tryUpdateFeatureVariable(it.featureKey, it.variableKey, defaultValue = it.defaultValue, changedValue = it.value)) {
                    ValidateChange.Failed -> {
                        //TODO update user about the failure
                    }

                    is ValidateChange.Success -> {
                        /**
                         * Should Inform the user about value save successfully
                         */
                        fetchFeatureFlags()
                    }

                    ValidateChange.NoDifference -> {

                    }
                }
            }
        }
    }

    fun onValueChanged(feature: Feature, variableMap: Pair<String, Any>, value: String) {
        logger.get().debug(message = "FeatureFlagViewModel ::onValueChanged started value=$value, variableMap=$variableMap")
        if (variableMap.second != value) {
            val list = valueChangedItems.value.toMutableList()
            val previousItem = list.find { it.featureKey == feature.name && it.variableKey == variableMap.first }
            if (previousItem != null) {
                list.remove(previousItem)
            }
            list.add(
                previousItem?.copy(
                    value = value,
                    defaultValue = variableMap.second
                ) ?: FeatureKeyVariable(
                    featureKey = feature.name,
                    variableKey = variableMap.first,
                    value = value,
                    defaultValue = variableMap.second
                )
            )
            valueChangedItems.value = list
        }

    }
}