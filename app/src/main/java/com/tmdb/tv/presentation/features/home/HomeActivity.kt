package com.tmdb.tv.presentation.features.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.polyak.iconswitch.IconSwitch.Checked
import com.tmdb.tv.databinding.ActivityHomeBinding
import com.tmdb.tv.presentation.features.home.adapter.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private var movieAdapter: MovieAdapter? = null

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

        viewModel.movies.observe(this, Observer {

            if(it.isNotEmpty()){
                movieAdapter = MovieAdapter(it)
                binding.rcvMovies.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = movieAdapter
                }
                binding.iconSwitch.visibility = View.VISIBLE
                binding.lnlHolder.visibility = View.GONE
                binding.lnlMovie.visibility = View.VISIBLE
            }else{
                binding.lnlMovie.visibility = View.GONE
                binding.iconSwitch.visibility = View.GONE
                binding.lnlHolder.visibility = View.VISIBLE
            }

        })
    }
}