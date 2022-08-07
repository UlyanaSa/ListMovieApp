package com.osvin.listmovieapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osvin.listmovieapp.domain.AppRepository
import com.osvin.listmovieapp.entity.NewMovie
import kotlinx.coroutines.launch

class MovieViewModel(private val appRepository: AppRepository): ViewModel() {

    private val _movieListLiveData = MutableLiveData<List<NewMovie>>()
    val movieListLiveData: LiveData<List<NewMovie>> = _movieListLiveData

    fun getAllMovies(){
        viewModelScope.launch {
            appRepository.transformationMovieList().collect{
                _movieListLiveData.value = it
            }
        }
    }


}