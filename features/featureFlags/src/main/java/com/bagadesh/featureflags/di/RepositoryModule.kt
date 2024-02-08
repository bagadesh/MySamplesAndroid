package com.bagadesh.featureflags.di

import com.bagadesh.featureflags.parseImpl.FeatureFlagParserImpl
import com.bagadesh.featureflags.parser.FeatureFlagParser
import com.bagadesh.featureflags.repository.FeatureFlagRepository
import com.bagadesh.featureflags.repositoryImpl.FeatureFlagRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by bagadesh on 14/04/23.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFeatureFlagParser(featureFlagParserImpl: FeatureFlagParserImpl): FeatureFlagParser

    @Binds
    @Singleton
    abstract fun bindFeatureFlagRepositoryImpl(featureFlagRepositoryImpl: FeatureFlagRepositoryImpl): FeatureFlagRepository
}