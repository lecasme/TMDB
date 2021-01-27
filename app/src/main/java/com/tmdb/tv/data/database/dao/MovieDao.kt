package com.tmdb.tv.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmdb.tv.data.entities.MovieEntity
import com.tmdb.tv.domain.models.Movie

@Dao
abstract class MoviesDao : BaseDao<MovieEntity> {

    @Query("SELECT * FROM movie")
    abstract suspend fun selectMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovies(obj: List<MovieEntity>)

}