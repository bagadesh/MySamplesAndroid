package com.bagadesh.myallsampleapps

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by bagadesh on 08/02/23.
 */
@HiltAndroidApp
class MyAllSampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}