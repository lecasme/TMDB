package com.tmdb.tv.presentation.features.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.tv.domain.models.Video
import com.tmdb.tv.domain.usecases.LandingUseCase
import kotlinx.coroutines.launch

class LandingViewModel(private val landingUseCase: LandingUseCase) : ViewModel() {

    private val _videos = MediatorLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos


    fun fetchVideos(movieId: Int) = viewModelScope.launch {
        _videos.addSource(landingUseCase.fetchVideos(movieId)){
            _videos.value = it
        }
    }

}