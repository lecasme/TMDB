package com.tmdb.tv.presentation.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.tv.domain.usecases.SplashUseCase
import kotlinx.coroutines.launch

class SplashViewModel(private val splashUseCase: SplashUseCase) : ViewModel() {


    private val _status = MediatorLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() =_status

    init {
        load()
    }

     private fun load() = viewModelScope.launch {
        _status.addSource(splashUseCase.fetchMovies(LIST)){
            _status.value = it
        }
    }

    companion object{
        // THE LIST OF MOVIES TO LOAD
        const val LIST = 1
    }

}