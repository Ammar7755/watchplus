package com.example.movie.network

import com.example.movie.homePage.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("language") language: String?,
        @Query("page") page: String?,
        @Query("sort_by") sortBY: String?,
    ): Response<MovieResponse>
}



