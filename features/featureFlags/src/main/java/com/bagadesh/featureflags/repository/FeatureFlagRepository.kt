package com.bagadesh.featureflags.repository

import com.bagadesh.featureflags.domain.Feature
import com.bagadesh.featureflags.domain.ValidateChange

/**
 * Created by bagadesh on 15/04/23.
 */
interface FeatureFlagRepository {

    suspend fun getFeatures(): List<Feature>

    suspend fun setFeatureEnabled(key: String, enabled: Boolean)

    suspend fun tryUpdateFeatureVariable(featureKey: String, variableKey: String, defaultValue: Any, changedValue: String): ValidateChange

}