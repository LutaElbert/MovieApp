package com.group.movieapp.ui.data.repository

import com.group.movieapp.ui.data.model.MovieResponse
import com.group.movieapp.ui.data.network.MovieApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApiService: MovieApiService) {
    suspend fun getPopularMovies(apiKey: String, page: Int = 1): MovieResponse{
        return movieApiService.getPopularMovies(apiKey, page)
    }
}