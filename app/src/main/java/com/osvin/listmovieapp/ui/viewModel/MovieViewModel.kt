package com.osvin.listmovieapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osvin.listmovieapp.domain.AppRepository
import com.osvin.listmovieapp.entity.NewMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor(private val appRepository: AppRepository): ViewModel() {

    private var _movieListLiveData = MutableLiveData<List<NewMovie>>()
    val movieListLiveData: LiveData<List<NewMovie>> = _movieListLiveData

    private var _positionLiveData = MutableLiveData<Int>()
    val positionLiveData: LiveData<Int> = _positionLiveData

    private var _titleMovieLiveData = MutableLiveData<String>()
    val titleMovieLiveData: LiveData<String> = _titleMovieLiveData

    fun isCheckedMovie(movie: NewMovie){
        _titleMovieLiveData.value = movie.title
    }

    fun saveData(data: List<NewMovie>){
        _movieListLiveData.value = data
    }

    fun savePosition(position: Int){
        _positionLiveData.value = position
    }

    fun getAllMovies(){
        viewModelScope.launch {
            appRepository.transformationMovieList().collect{
                _movieListLiveData.value = it
            }
        }
    }


}