package com.tmdb.tv.presentation.features.landing

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.tmdb.tv.R
import com.tmdb.tv.databinding.ActivityHomeBinding
import com.tmdb.tv.databinding.ActivityLandingBinding
import com.tmdb.tv.domain.models.Movie
import com.tmdb.tv.presentation.features.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LandingActivity : AppCompatActivity() {

    private val viewModel: LandingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.extras?.getSerializable("movie") as Movie
        viewModel.fetchVideos(movie.id)

        //binding.txtDescription.text = movie.overview

        /*setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

    }
}