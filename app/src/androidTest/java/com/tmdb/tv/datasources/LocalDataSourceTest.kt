package com.tmdb.tv.datasources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tmdb.tv.data.datasource.local.MovieLocalDataSource
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class LocalDataSourceTest : KoinTest {

    private val movieLocalDataSource: MovieLocalDataSource by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun selectMovies() {
        runBlocking {
            // Movie table can not be false when splash screen is already loaded
            assertFalse(movieLocalDataSource.selectMovies().isEmpty())
        }

    }

}