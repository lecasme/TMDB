package com.tmdb.tv.presentation.features.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.polyak.iconswitch.IconSwitch.Checked
import com.tmdb.tv.databinding.ActivityHomeBinding
import com.tmdb.tv.domain.utils.Connectivity
import com.tmdb.tv.domain.utils.setOnSafeClickListener
import com.tmdb.tv.presentation.features.home.adapter.MovieAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private var movieAdapter: MovieAdapter? = null
    private val connectivity: Connectivity by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.onTextChanged =
            OnTextChanged { s, _, _, _ ->
                movieAdapter?.filter?.filter(s)
            }

        binding.iconSwitch.setCheckedChangeListener { current ->
            when (current) {
                Checked.LEFT -> movieAdapter?.orderByPopularity()
                Checked.RIGHT -> movieAdapter?.orderByRating()
            }
        }

        binding.btnRetry.setOnSafeClickListener(5000) {
            if(connectivity.isOnline()){
                Snackbar.make(binding.coordinator, "Buscando peliculas...", Snackbar.LENGTH_LONG).show()
                viewModel.fetchMovies()
            }else{
               Snackbar.make(binding.coordinator, "Oh no! No estás conectado al red :(", Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.movies.observe(this, Observer {
            if (it.isNotEmpty()) {
                movieAdapter = MovieAdapter(it)
                binding.rcvMovies.apply {
                    layoutManager = GridLayoutManager(context, 3)
                    adapter = movieAdapter
                }
                binding.iconSwitch.visibility = View.VISIBLE
                binding.lnlHolder.visibility = View.GONE
                binding.lnlMovie.visibility = View.VISIBLE
            } else {
                binding.lnlMovie.visibility = View.GONE
                binding.iconSwitch.visibility = View.GONE
                binding.lnlHolder.visibility = View.VISIBLE
            }
        })
    }
}