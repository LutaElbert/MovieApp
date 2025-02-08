package com.group.movieapp.ui.data.repository.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun <T> save(key: String, value: T)

    suspend fun read(key: String): String?

    suspend fun remove(key: String)

    val userIDName: Flow<String>
}