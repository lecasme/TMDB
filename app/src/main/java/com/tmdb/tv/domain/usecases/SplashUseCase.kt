package com.tmdb.tv.domain.usecases

import androidx.lifecycle.LiveData
import com.tmdb.tv.data.repository.MovieRepository

class SplashUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend fun fetchMovies(list: Int) : LiveData<Boolean>{
       return movieRepository.fetchMovies(list)
    }

}