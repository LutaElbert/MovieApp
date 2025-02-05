package com.group.movieapp.ui.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    @SerialName("overview")
    val description: String? =  null,
    @SerialName("release_date")
    val releaseYear: String? = null,
    @SerialName("poster_path")
    val imageUrl: String? = null,
    @SerialName("vote_average")
    val rating: Double
)

@Serializable
data class MovieResponse(
    @SerialName("results") val movies: List<Movie>
)


