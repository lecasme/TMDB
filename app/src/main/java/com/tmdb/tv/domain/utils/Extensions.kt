package com.tmdb.tv.domain.utils
import android.view.View
import com.tmdb.tv.data.entities.MovieEntity
import com.tmdb.tv.domain.models.Movie

fun MovieEntity.toModel(): Movie {
    return Movie(this.id, this.originalTitle, this.posterPath, this.releaseDate, this.popularity, this.voteAverage, this.voteCount, this.backdropPath, this.overview, this.adult, this.originalLanguage)
}

fun List<MovieEntity>.toListedModel(): List<Movie> {
    val movies = mutableListOf<Movie>()
    this.forEach{ movies.add(it.toModel()) }
    return movies
}

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}