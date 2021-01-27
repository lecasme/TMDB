package com.tmdb.tv.data.service

import com.tmdb.tv.domain.models.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("list/{list}")
    suspend fun fetchMovies(@Path("list") list: Int): Response

    @GET("movie/{movieId}/videos")
    suspend fun fetchVideos(@Path("movieId") movieId: Int): Response

}