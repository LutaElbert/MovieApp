package com.group.movieapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.movieapp.ui.data.repository.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewmodel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    fun save(key: String, value: String) {
        viewModelScope.launch {
            userPreferencesRepository.save(key, value)
        }
    }

    suspend fun read(key: String) : String {
        return userPreferencesRepository.read(key).toString()
    }

    fun remove(key: String) {
        viewModelScope.launch {
            userPreferencesRepository.remove(key)
        }
    }
}