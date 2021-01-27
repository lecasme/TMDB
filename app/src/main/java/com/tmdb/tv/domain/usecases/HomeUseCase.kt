package com.tmdb.tv.domain.usecases

import androidx.lifecycle.LiveData
import com.tmdb.tv.data.repository.MovieRepository
import com.tmdb.tv.domain.models.Movie

class HomeUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend fun getMovies(): LiveData<List<Movie>> {
        return movieRepository.getMovies()
    }

}