package com.bagadesh.featureflags.domain

/**
 * Created by bagadesh on 14/04/23.
 */
sealed interface FeatureFlagUiState {

    object Loading : FeatureFlagUiState

    class Success(val features: List<Feature>): FeatureFlagUiState

}