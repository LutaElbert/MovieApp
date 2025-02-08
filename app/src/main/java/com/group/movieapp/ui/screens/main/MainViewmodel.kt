package com.group.movieapp.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.movieapp.ui.data.model.Movie
import com.group.movieapp.ui.data.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(private val useCase: GetPopularMoviesUseCase): ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    private val movies: StateFlow<List<Movie>> = _movies

    val networkState = MutableStateFlow<MovieUIState>(MovieUIState.Loading)

    private var _firstMovie = MutableStateFlow<Movie?>(null)
    val firstMovie: StateFlow<Movie?> = _firstMovie


    init {
        fetchPopularMovies("9be1ee60d5728453f1977de553d81b86")
    }

    private fun fetchPopularMovies(accessKey: String) {
        viewModelScope.launch {
            try {
                val moviesResponse = useCase.invoke(accessKey, 1)

                _movies.value = moviesResponse.movies
                _firstMovie.value = moviesResponse.movies.first()

                networkState.value = MovieUIState.Success(movies.value)
            } catch (exception: Exception) {
                networkState.value = MovieUIState.Error("Failed to fetch movies: ${exception.message}")
            }
        }
    }

    fun getFirstMovie(): Movie? = _firstMovie.value
}

sealed class MovieUIState {
    data object Loading: MovieUIState()
    data class Success(val movies: List<Movie>): MovieUIState()
    data class Error(val message: String): MovieUIState()
}