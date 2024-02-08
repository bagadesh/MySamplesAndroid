@file:Suppress("UNCHECKED_CAST")

package com.bagadesh.featureflags.library

import com.bagadesh.featureflags.logger.Logger

/**
 * Created by bagadesh on 15/04/23.
 */
class FeatureFlagReaderImpl constructor(
    private val featureMeteor: FeatureMeteor,
    private val logger: Logger,
) : FeatureFlagReader {


    override fun isFeatureEnabled(name: String): Boolean {
        return featureMeteor.featuresMap[name]?.enabled ?: false
    }

    override fun <T> getVariableValue(featureKey: String, variableKey: String, classOfT: Class<T>, defaultValue: () -> T): T {
        val feature = featureMeteor.featuresMap[featureKey] ?: return defaultValue().also { logger.error(message = "FeatureFlagReader ::getVariableValue feature not available in in-memory cache") }
        val variable = feature.variableMap[variableKey] ?: return defaultValue().also { logger.error(message = "FeatureFlagReader ::getVariableValue given variableKey not available in the feature") }
        return variable.value as? T ?: return defaultValue().also { logger.error(message = "FeatureFlagReader ::getVariableValue cannot able to cast ${variable.value}") }
    }
}