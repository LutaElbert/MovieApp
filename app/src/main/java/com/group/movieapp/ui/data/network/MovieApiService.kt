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

    private val baseUrl = "https://api.themoviedb.org/3"

    suspend fun getPopularMovies(apiKey: String, page: Int = 1): MovieResponse {
        return client.get("$baseUrl/movie/popular") {
            header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YmUxZWU2MGQ1NzI4NDUzZjE5NzdkZTU1M2Q4MWI4NiIsIm5iZiI6MTczODQ2NjEwNS40NDQsInN1YiI6IjY3OWVlMzM5YjA2NDM3YTRlZTI2M2M1OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8ej1B-yFPkkUjcXEBaruowittS1upTLqrlPr1sRhu28")
            parameter("api_key", apiKey)
            parameter("page", page)
            contentType(ContentType.Application.Json)
        }.body()
    }

}