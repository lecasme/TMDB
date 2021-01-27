package com.tmdb.tv.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tmdb.tv.data.datasource.local.MovieLocalDataSource
import com.tmdb.tv.data.datasource.remote.MovieRemoteDataSource
import com.tmdb.tv.domain.models.Movie
import com.tmdb.tv.domain.models.Video
import com.tmdb.tv.domain.utils.Connectivity
import com.tmdb.tv.domain.utils.toListedModel
import com.tmdb.tv.domain.utils.toModel


interface MovieRepository {

    // Cloud
    suspend fun fetchMovies(limit: Int): LiveData<Boolean>
    suspend fun fetchVideos(movieId: Int): LiveData<List<Video>>
    // Local
    suspend fun getMovies(): LiveData<List<Movie>>

}

class MovieRepositoryImpl(private val movieRemoteDataSource: MovieRemoteDataSource,
                          private val movieLocalDataSource: MovieLocalDataSource,
                          private val connectivity: Connectivity): MovieRepository {

    private val resultMovie = MutableLiveData<List<Movie>>()
    private val resultVideo = MutableLiveData<List<Video>>()
    private val status = MutableLiveData<Boolean>()

    override suspend fun fetchMovies(list: Int): LiveData<Boolean> {
        if(connectivity.isOnline()){
            val response = movieRemoteDataSource.fetchMovies(list)
            movieLocalDataSource.insertMovies(response.items)
            status.postValue(true)
        }else{
            status.postValue(false)
        }
        return status
    }

    override suspend fun getMovies(): LiveData<List<Movie>> {
        val movies = movieLocalDataSource.selectMovies()
        resultMovie.postValue(movies.toListedModel())
        return resultMovie
    }

    override suspend fun fetchVideos(movieId: Int): LiveData<List<Video>> {
        val response = movieRemoteDataSource.fetchVideos(movieId)
        resultVideo.postValue(response.results)
        return resultVideo
    }

}