package com.bagadesh.featureflags.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by bagadesh on 15/04/23.
 */

// At the top level of your kotlin file:
val Context.myDataStore: DataStore<Preferences> by preferencesDataStore(name = "featureFlags")

class PreferenceImpl @Inject constructor(
    private val preference: DataStore<Preferences>
): PreferenceWrapper {

    override suspend fun getString(key: String, default: () -> String): String {
        val preferenceKey = stringPreferencesKey(key)
        val flow = preference.data.map {
            it[preferenceKey] ?: default()
        }
        return flow.first()
    }

    override suspend fun putString(key: String, value: String) {
        preference.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun getBoolean(key: String, default: () -> Boolean): Boolean {
        val preferenceKey = booleanPreferencesKey(key)
        val flow = preference.data.map {
            it[preferenceKey] ?: default()
        }
        return flow.first()
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        preference.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun getInt(key: String, default: () -> Int): Int {
        val preferenceKey = intPreferencesKey(key)
        val flow = preference.data.map {
            it[preferenceKey] ?: default()
        }
        return flow.first()
    }

    override suspend fun putInt(key: String, value: Int) {
        preference.edit {
            it[intPreferencesKey(key)] = value
        }
    }
}