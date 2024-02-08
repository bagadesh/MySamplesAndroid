package com.bagadesh.featureflags.domain

/**
 * Created by bagadesh on 14/04/23.
 */
data class Feature(
    val name: String,
    val enabled: Boolean,
    val variables: List<FeatureVariable> = emptyList()
) {
    val variableMap: Map<String, FeatureVariable> = variables.associateBy { it.name }
}

data class FeatureVariable(
    val name: String,
    val value: Any
)