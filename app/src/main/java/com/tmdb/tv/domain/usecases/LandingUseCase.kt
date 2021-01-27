package com.tmdb.tv.domain.usecases

import androidx.lifecycle.LiveData
import com.tmdb.tv.data.repository.MovieRepository
import com.tmdb.tv.domain.models.Video

class LandingUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend fun fetchVideos(movieId: Int): LiveData<List<Video>> {
        return movieRepository.fetchVideos(movieId)
    }

}