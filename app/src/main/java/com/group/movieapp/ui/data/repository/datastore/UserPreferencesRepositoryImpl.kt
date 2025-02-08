package com.group.movieapp.ui.data.repository.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

const val MOVIE_PREFERENCES = "movie_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MOVIE_PREFERENCES)

class UserPreferencesRepositoryImpl (
    private val context: Context
) : UserPreferencesRepository {

    override val userIDName: Flow<String> = flowOf("userIDName")

    private val dataStore = context.dataStore

    override suspend fun <T> save(key: String, value: T) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value.toString()
        }
    }

    override suspend fun read(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        val preferences = dataStore.data.firstOrNull()
        return preferences?.get(prefKey)
    }

    override suspend fun remove(key: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences.remove(prefKey)
        }
    }
}