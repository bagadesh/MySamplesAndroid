package com.bagadesh.featureflags.parser

import com.bagadesh.featureflags.domain.Feature

/**
 * Created by bagadesh on 14/04/23.
 */

interface FeatureFlagParser {

    suspend fun parser(): List<Feature>

}