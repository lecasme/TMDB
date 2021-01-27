package com.tmdb.tv.presentation.features.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tmdb.tv.R
import com.tmdb.tv.domain.models.Movie
import com.tmdb.tv.domain.utils.setOnSafeClickListener
import com.tmdb.tv.presentation.features.home.HomeActivity
import com.tmdb.tv.presentation.features.landing.LandingActivity
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(), Filterable {

    lateinit var context: Context
    var copiedMovies = listOf<Movie>()

    init {
        copiedMovies = movies.sortedByDescending { it.popularity }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgMovie)
        val cardView: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder  {

        context = viewGroup.context
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.movie_item,
                viewGroup,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val movie = copiedMovies[position]
        Glide
            .with(context)
            .load("$API_IMG_URL${movie.posterPath}")
            .placeholder(R.drawable.holder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.imageView)

        viewHolder.cardView.setOnSafeClickListener {
            val intent = Intent(context, LandingActivity::class.java)
            intent.putExtra("movie", movie)
            context.startActivity(intent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = copiedMovies.size

    companion object{
        const val API_IMG_URL = "https://image.tmdb.org/t/p/w500"
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                copiedMovies = if (charSearch.isEmpty()) {
                    movies
                } else {
                    val resultList = mutableListOf<Movie>()
                    for (movie in movies) {
                        if (movie.originalTitle.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(movie)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = copiedMovies
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    copiedMovies = it.values as ArrayList<Movie>
                    notifyDataSetChanged()
                }
            }

        }
    }

    fun orderByPopularity() {
        copiedMovies = copiedMovies.sortedByDescending { it.popularity }
        notifyDataSetChanged()
    }

    fun orderByRating() {
        copiedMovies = copiedMovies.sortedByDescending { it.voteAverage }
        notifyDataSetChanged()
    }

}