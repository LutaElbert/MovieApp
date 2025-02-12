package com.group.movieapp.ui.data.network

import com.group.movieapp.ui.data.model.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class MovieApiService @Inject constructor(private val client: HttpClient) {

    private val baseUrl = ApiConfig.BASE_URL

    suspend fun getPopularMovies(apiKey: String, page: Int = 1): MovieResponse {
        return client.get("$baseUrl/movie/popular") {
            parameter("api_key", apiKey)
            parameter("page", page)
            contentType(ContentType.Application.Json)
        }.body()
    }

}