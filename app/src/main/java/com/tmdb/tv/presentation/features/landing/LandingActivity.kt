package com.tmdb.tv.presentation.features.landing

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.tmdb.tv.R
import com.tmdb.tv.databinding.ActivityHomeBinding
import com.tmdb.tv.databinding.ActivityLandingBinding
import com.tmdb.tv.databinding.ActivitySplashBinding
import com.tmdb.tv.domain.models.Movie
import com.tmdb.tv.domain.utils.dateFormat
import com.tmdb.tv.presentation.features.home.HomeViewModel
import com.tmdb.tv.presentation.features.home.adapter.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class LandingActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    //private val viewModel: LandingViewModel by viewModel()
    private var videoKey: String? = null
    lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.extras?.getSerializable("movie") as Movie
        //viewModel.fetchVideos(movie.id)

        Glide
            .with(this)
            .load("${MovieAdapter.API_IMG_URL}${movie.backdropPath}")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgBackdropPath)

        binding.ratingBar.rating = movie.voteAverage.toFloat()/2
        binding.txtTitle.text = movie.originalTitle
        binding.txtAverage.text = movie.voteAverage.toString()
        binding.scroll.txtDescription.text = movie.overview

        val adult = if (movie.adult) "Todos los publicos" else "Mayores de 18"
        val information = " $adult ● ${movie.releaseDate.dateFormat()} ● ${movie.voteCount} votes"
        binding.txtInfo.text = information



        binding.scroll.lnlPlayer.visibility = View.VISIBLE
        videoKey = "7SlILk2WMTI"
        binding.scroll.youtubePlayer.initialize(YOUTUBE_KEY, this)


        /*viewModel.videos.observe(this, Observer {
            if(it.isNotEmpty()){
                binding.scroll.lnlPlayer.visibility = View.VISIBLE
                videoKey = it[0].key
                binding.scroll.youtubePlayer.initialize(YOUTUBE_KEY, this)
            }
        })*/

    }

    companion object{
        const val YOUTUBE_KEY = "AIzaSyDXMDNgSyNeM5lhDxIJUM1GIb46cWyBUuM"
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, b: Boolean) {
        if(!b){
            youTubePlayer?.loadVideo(videoKey)
            youTubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        Log.e("Youtube", "Load Failure")
    }
}