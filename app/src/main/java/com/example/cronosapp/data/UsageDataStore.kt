package com.example.cronosapp.data

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "usage_data_store")

class UsageDataStore(private val context: Context) {

    private val activityKey = intPreferencesKey("activity_")

    suspend fun getUsageCount(activityName: String): Int {
        val preferences = context.dataStore.data.first()
        return preferences[activityKey(activityName)] ?: 0
    }

    suspend fun incrementUsageCount(activityName: String) {
        context.dataStore.edit { preferences ->
            val currentCount = preferences[activityKey(activityName)] ?: 0
            preferences[activityKey(activityName)] = currentCount + 1
        }
    }

    private fun activityKey(activityName: String) = intPreferencesKey("activity_$activityName")
}

