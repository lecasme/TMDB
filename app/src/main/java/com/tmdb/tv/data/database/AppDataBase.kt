package com.tmdb.tv.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmdb.tv.data.database.dao.MoviesDao
import com.tmdb.tv.data.entities.MovieEntity
import com.tmdb.tv.domain.models.Movie

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    // DAO
    abstract fun movieDao(): MoviesDao

    companion object {
        private const val databaseName = "Movie.db"
        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, databaseName).build()
    }
}