package com.tmdb.tv.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie(val id: Int,
          val originalTitle: String,
          val posterPath: String,
          val releaseDate: String,
          val popularity: Double,
          val voteAverage: Double,
          val voteCount: Int,
          val backdropPath: String,
          val overview: String,
          val adult: Boolean,
          val originalLanguage: String) : Serializable


