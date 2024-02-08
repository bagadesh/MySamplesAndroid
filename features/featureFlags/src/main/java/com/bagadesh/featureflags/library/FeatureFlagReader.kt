package com.bagadesh.featureflags.library

/**
 * Created by bagadesh on 15/04/23.
 */
interface FeatureFlagReader {

    fun isFeatureEnabled(name: String): Boolean

    fun <T> getVariableValue(featureKey: String, variableKey: String, classOfT: Class<T>, defaultValue: () -> T): T

}