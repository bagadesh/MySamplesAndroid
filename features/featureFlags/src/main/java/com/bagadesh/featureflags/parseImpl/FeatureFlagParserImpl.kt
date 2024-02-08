package com.bagadesh.featureflags.parseImpl

import android.content.Context
import androidx.annotation.Keep
import com.bagadesh.featureflags.R
import com.bagadesh.featureflags.domain.Feature
import com.bagadesh.featureflags.domain.FeatureVariable
import com.bagadesh.featureflags.parser.FeatureFlagParser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by bagadesh on 14/04/23.
 */
class FeatureFlagParserImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : FeatureFlagParser {

    override suspend fun parser(): List<Feature> {
        val featureFlagJson = context.resources.openRawResource(R.raw.feature_flag).bufferedReader().use { it.readText() }
        val result = gson.fromJson<Map<String, Map<String, Any>>>(featureFlagJson, object : TypeToken<Map<String, Map<String, Any>>>() {}.type)
        val mappedResult = result.map {
            val enabled = it.value["enabled"] as Boolean
            val variables = it.value["variables"] as Map<String, Any>
            Feature(
                name = it.key,
                enabled = enabled,
                variables = variables.map { variable ->
                    FeatureVariable(
                        name = variable.key,
                        value = variable.value
                    )
                }
            )
        }
        return mappedResult
    }

}

@Keep
class FeatureFlagJson(
    val map: Map<String, Map<String, Any>> = emptyMap()
)