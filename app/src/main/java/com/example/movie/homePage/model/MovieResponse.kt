package com.example.movie.homePage.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    @SerializedName("id")
    val movieId: Int,
    @SerializedName("original_title")
    val movieTitle: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)