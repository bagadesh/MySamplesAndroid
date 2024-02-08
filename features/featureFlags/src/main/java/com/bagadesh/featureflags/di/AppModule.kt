package com.bagadesh.featureflags.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import com.bagadesh.featureflags.logger.Logger
import com.bagadesh.featureflags.logger.impl.LoggerImpl
import com.bagadesh.featureflags.preference.PreferenceImpl
import com.bagadesh.featureflags.preference.PreferenceWrapper
import com.bagadesh.featureflags.preference.myDataStore
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by bagadesh on 14/04/23.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindLoggerImpl(loggerImpl: LoggerImpl): Logger

    @Binds
    @Singleton
    abstract fun bindPreferenceImpl(preferenceImpl: PreferenceImpl): PreferenceWrapper

    companion object {
        @Provides
        @Singleton
        fun providesGson(): Gson {
            return Gson()
        }

        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
            return context.myDataStore
        }
    }

}