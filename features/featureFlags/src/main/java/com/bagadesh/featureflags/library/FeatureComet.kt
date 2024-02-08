package com.bagadesh.featureflags.library

import android.content.Context
import com.bagadesh.featureflags.logger.impl.LoggerImpl
import com.bagadesh.featureflags.parseImpl.FeatureFlagParserImpl
import com.bagadesh.featureflags.preference.PreferenceImpl
import com.bagadesh.featureflags.preference.myDataStore
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Created by bagadesh on 15/04/23.
 */
object FeatureComet {

    private lateinit var meteor: FeatureMeteor

    fun initialise(init: FeatureCometBuilder.() -> Unit) {
        val builder = FeatureCometBuilder().apply(init)
        builder.verifyRequiredParameters()
        val gson = Gson()
        val context = builder.context!!
        val featureFlagParser = FeatureFlagParserImpl(context = context, gson = gson)
        val preference = PreferenceImpl(preference = context.myDataStore)
        val logger = LoggerImpl()
        val dispatcher = WrapDispatcherImpl()
        meteor = FeatureMeteor(
            featureFlagParser = featureFlagParser, preference = preference,
            logger = logger, gson = gson, dispatcher = dispatcher
        )
    }

}

data class FeatureCometBuilder(
    val context: Context? = null,
) {
    fun verifyRequiredParameters() {
        requireNotNull(context)
    }
}