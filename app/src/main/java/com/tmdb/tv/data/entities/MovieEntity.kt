package com.tmdb.tv.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tmdb.tv.domain.models.Movie
import java.io.Serializable

@Entity(tableName = "Movie")
data class MovieEntity (

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("original_language")
    val originalLanguage: String,
)
