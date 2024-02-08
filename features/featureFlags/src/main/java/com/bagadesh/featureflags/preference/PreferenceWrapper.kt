package com.bagadesh.featureflags.preference

/**
 * Created by bagadesh on 15/04/23.
 */
interface PreferenceWrapper {

    suspend fun getString(key: String, default: () -> String): String

    suspend fun putString(key: String, value: String)

    suspend fun getInt(key: String, default: () -> Int): Int

    suspend fun putInt(key: String, value: Int)

    suspend fun getBoolean(key: String, default: () -> Boolean): Boolean

    suspend fun putBoolean(key: String, value: Boolean)

}