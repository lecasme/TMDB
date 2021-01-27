package com.tmdb.tv.domain.models

import com.google.gson.annotations.SerializedName
import com.tmdb.tv.data.entities.MovieEntity

data class Response(

    @SerializedName("created_by")
    val createdBy: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("favorite_count")
    val favoriteCount: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("items")
    val items : List<MovieEntity>,

    @SerializedName("results")
    val results : List<Video>

    )