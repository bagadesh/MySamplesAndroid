package com.bagadesh.featureflags

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bagadesh.featureflags.domain.Feature
import com.bagadesh.featureflags.domain.FeatureFlagUiState
import com.bagadesh.featureflags.ui.FeatureDetailUI
import com.bagadesh.featureflags.ui.expanded.ExpandedVariablesBackground
import com.bagadesh.featureflags.ui.expanded.ShowFeatureKeyDetailsUI
import com.bagadesh.featureflags.vm.FeatureFlagViewModel

/**
 * Created by bagadesh on 14/04/23.
 */

@Composable
fun FeatureFlagScreen(vm: FeatureFlagViewModel = hiltViewModel()) {
    val featureFlags by vm.featureFlag.collectAsState()
    val expandedItems by vm.expandedItems.collectAsState()
    FeatureFlagScreenUI(
        featureFlags = featureFlags,
        expandedItems = expandedItems,
        contentClick = vm::onContentClick,
        setFeatureChecked = vm::setFeatureChecked,
        saveChangesClick = vm::saveChangesClick,
        onValueChanged = vm::onValueChanged
    )
}


@Composable
fun FeatureFlagScreenUI(
    modifier: Modifier = Modifier,
    featureFlags: FeatureFlagUiState,
    expandedItems: List<String>,
    contentClick: (String) -> Unit,
    setFeatureChecked: (name: String, enabled: Boolean) -> Unit,
    onValueChanged: (feature: Feature, variableMap: Pair<String, Any>, value: String) -> Unit,
    saveChangesClick: () -> Unit,
) {
    FeatureFlagUiStateBackground(
        modifier = modifier,
        featureFlagUiState = featureFlags
    ) { listOfFeatures ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(listOfFeatures) { featureFlag ->
                val expanded = expandedItems.contains(featureFlag.name)
                var checked by remember(featureFlag.enabled) {
                    mutableStateOf(featureFlag.enabled)
                }
                FeatureDetailUI(
                    title = featureFlag.name,
                    description = featureFlag.name,
                    expanded = expanded,
                    isChecked = checked,
                    contentClick = {
                        if (expanded) {
                            saveChangesClick()
                        }
                        contentClick(featureFlag.name)
                    },
                    onCheckBoxClicked = {
                        checked = it
                        setFeatureChecked(featureFlag.name, it)
                    },
                    iconButtonClick = {},
                    expandedContent = {
                        val featureKeyList = remember { featureFlag.variables.map { it.name to it.value } }
                        ExpandedVariablesBackground(list = featureKeyList, expanded) {
                            ShowFeatureKeyDetailsUI(
                                key = it.first,
                                value = it.second,
                                onValueChange = { changedValue ->
                                    onValueChanged(featureFlag, it, changedValue)
                                },
                                onFocusChange = { isFocused ->
                                    if (!isFocused) {
                                        saveChangesClick()
                                    }
                                }
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun FeatureFlagUiStateBackground(
    modifier: Modifier = Modifier,
    featureFlagUiState: FeatureFlagUiState,
    content: @Composable (List<Feature>) -> Unit
) {
    Box(modifier = modifier) {
        when (featureFlagUiState) {
            FeatureFlagUiState.Loading -> CircularProgressIndicator()
            is FeatureFlagUiState.Success -> content(featureFlagUiState.features)
        }
    }
}
