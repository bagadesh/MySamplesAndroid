package com.bagadesh.featureflags.ui.domain

/**
 * Created by bagadesh on 15/04/23.
 */
data class FeatureKeyVariable(
    val featureKey: String,
    val variableKey: String,
    val defaultValue: Any,
    val value: String,
)
