package com.example.movie.homePage.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.network.TMDBService
import com.example.movie.homePage.model.Movie
import com.example.movie.network.ServiceBuilder
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>?>() // mutable read-write
    val movieObserver: LiveData<List<Movie>?>  // immutable read-access only
        get() = _movies
    private val userService = ServiceBuilder.createService(TMDBService::class.java)

    fun getMovie() {
        viewModelScope.launch {
            try {
                val movieResponse = userService.discoverMovie("en-US", "1", "popularity.desc")
                // TODO: check the return type for user API
                _movies.value = movieResponse.body()?.results
                Log.i(
                    "getMovie:: ",
                    "inside try block movies size = ${_movies.value?.size.toString()}"
                )
            } catch (e: Exception) {
                // catch exception
                Log.e("getMovie:: ", "inside catch block ex =  ${e.message ?: ""}")
                _movies.value = null
            }
        }
    }
}