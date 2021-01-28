package com.tmdb.tv.datasources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tmdb.tv.data.database.dao.MoviesDao
import com.tmdb.tv.data.datasource.remote.MovieRemoteDataSource
import junit.framework.Assert
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class RemoteDataSourceTest : KoinTest {

    private val movieRemoteDataSource: MovieRemoteDataSource by inject()

    private val listID = 1
    private val movieID = 100402

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun selectMovies() {
        runBlocking {
            assertFalse(movieRemoteDataSource.fetchMovies(listID).items.isEmpty())
            assertFalse(movieRemoteDataSource.fetchVideos(movieID).results.isEmpty())
        }

    }

}