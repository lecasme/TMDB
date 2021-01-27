package com.tmdb.tv.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.tmdb.tv.domain.models.Movie
import com.tmdb.tv.domain.usecases.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _movies = MediatorLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        _movies.addSource(homeUseCase.getMovies()){
            _movies.value = it
        }
    }

    fun fetchMovies() = viewModelScope.launch {
        _movies.addSource(homeUseCase.getMovies()){
            _movies.value = it
        }
    }

}