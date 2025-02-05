package com.group.movieapp.ui.data.usecase

import com.group.movieapp.ui.data.model.MovieResponse
import com.group.movieapp.ui.data.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(apiKey: String, page: Int): MovieResponse {
        return repository.getPopularMovies(apiKey, page)
    }
}