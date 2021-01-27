package com.tmdb.tv.domain.utils
import android.util.Log
import android.view.View
import com.tmdb.tv.data.entities.MovieEntity
import com.tmdb.tv.domain.models.Movie
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun MovieEntity.toModel(): Movie {
    return Movie(
        this.id,
        this.originalTitle,
        this.posterPath,
        this.releaseDate,
        this.popularity,
        this.voteAverage,
        this.voteCount,
        this.backdropPath,
        this.overview,
        this.adult,
        this.originalLanguage
    )
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

fun String.dateFormat(): String {

    var newDate = this

    try {
        val formatOriginal = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formatRenewed = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val date = formatOriginal.parse(this)
        newDate = formatRenewed.format(date).toString()
    }
    catch (e: Exception) {
        Log.e("Date Converter", "This date string can`t be formated")
    }
    finally {
       return newDate
    }

}