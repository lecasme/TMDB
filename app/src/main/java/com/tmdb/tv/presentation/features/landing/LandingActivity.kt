package com.tmdb.tv.presentation.features.landing

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tmdb.tv.R
import com.tmdb.tv.databinding.ActivityHomeBinding
import com.tmdb.tv.databinding.ActivityLandingBinding
import com.tmdb.tv.domain.models.Movie
import com.tmdb.tv.presentation.features.home.HomeViewModel
import com.tmdb.tv.presentation.features.home.adapter.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class LandingActivity : AppCompatActivity() {

    private val viewModel: LandingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.extras?.getSerializable("movie") as Movie
        viewModel.fetchVideos(movie.id)

        Glide
            .with(this)
            .load("${MovieAdapter.API_IMG_URL}${movie.backdropPath}")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgBackdropPath)


        val textPopularity = "${movie.voteAverage} voted"
        val textRated =  "${movie.popularity.toInt()} popularity"

        binding.scroll.txtPopularity.text = textPopularity
        binding.scroll.txtRated.text = textRated

        binding.scroll.txtTitle.text = movie.originalTitle
        binding.scroll.txtDescription.text = movie.overview



    }
}