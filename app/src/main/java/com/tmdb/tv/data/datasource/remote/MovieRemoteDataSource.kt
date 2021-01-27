package com.tmdb.tv.data.datasource.remote

import com.tmdb.tv.data.service.MovieService
import com.tmdb.tv.domain.models.Response
import com.tmdb.tv.domain.utils.Connectivity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MovieRemoteDataSource {
    suspend fun fetchMovies(list: Int): Response
    suspend fun fetchVideos(movieId: Int): Response
}

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRemoteDataSource {

    override suspend fun fetchMovies(list: Int) = withContext(ioDispatcher) {
        movieService.fetchMovies(list)
    }

    override suspend fun fetchVideos(movieId: Int) = withContext(ioDispatcher) {
        movieService.fetchVideos(movieId)
    }
}