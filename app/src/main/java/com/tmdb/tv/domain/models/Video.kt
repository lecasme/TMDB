package com.tmdb.tv.domain.models

import com.google.gson.annotations.SerializedName
import com.tmdb.tv.data.entities.MovieEntity

data class Video(

    @SerializedName("id")
    val id: String,

    @SerializedName("key")
    val key: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("site")
    val site: String,

    @SerializedName("type")
    val type : String)