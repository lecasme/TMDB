package com.tmdb.tv.presentation.features.landing

import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.tmdb.tv.R
import com.tmdb.tv.databinding.ActivityLandingBinding
import com.tmdb.tv.domain.models.Movie
import com.tmdb.tv.domain.models.Video
import com.tmdb.tv.domain.usecases.LandingUseCase
import com.tmdb.tv.domain.utils.API_IMG_URL
import com.tmdb.tv.domain.utils.dateFormat
import com.tmdb.tv.presentation.features.home.adapter.MovieAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlinx.coroutines.*

class LandingActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val useCase: LandingUseCase by inject()
    private var videoKey: String? = null
    lateinit var binding: ActivityLandingBinding
    private var player: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.extras?.getSerializable("movie") as Movie

        Glide
            .with(this)
            .load("$API_IMG_URL${movie.backdropPath}")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgBackdropPath)

        binding.ratingBar.rating = movie.voteAverage.toFloat()/2
        binding.txtTitle.text = movie.originalTitle
        binding.txtAverage.text = movie.voteAverage.toString()
        binding.scroll.txtDescription.text = movie.overview
        binding.imgBack.setOnClickListener { onBackPressed() }

        val adult = if (movie.adult) resources.getString(R.string.landing_no_suitable) else resources.getString(R.string.landing_suitable)
        val information = " $adult ● ${movie.releaseDate.dateFormat()} ● ${movie.voteCount} ${resources.getString(R.string.landing_votes)}"
        binding.txtInfo.text = information


        // YouTubeBaseActivity no extiende de AppCompatActivity por lo que no se puede hacer uso del viewModel y se llamo directamente al caso de uso
        // YouTubePlayerView requiere trabajar con YouTubeBaseActivity
        // Podria evitarse esto no teniendo el reproductor en la misma actividad o si el id del video volviese en el detalle de las peliculas

        GlobalScope.launch(Dispatchers.IO) {
            delay(1000)
            useCase.fetchVideos(movie.id).let { data ->
                GlobalScope.launch(Dispatchers.Main) {
                    val videos = data.value as List<Video>
                    if(videos.isNotEmpty()){
                        binding.scroll.lnlPlayer.visibility = View.VISIBLE
                        videoKey = videos[0].key
                        binding.scroll.youtubePlayer.initialize(YOUTUBE_KEY, this@LandingActivity)
                    }
                }
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        player?.release()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, b: Boolean) {
        if(!b){
            player = youTubePlayer
            player?.loadVideo(videoKey)
            player?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        Log.e("Youtube", "Load Failure")
    }

    companion object{
        const val YOUTUBE_KEY = "AIzaSyDXMDNgSyNeM5lhDxIJUM1GIb46cWyBUuM"
    }


}