package com.example.themovielibrary.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovielibrary.home.api.Movie
import com.example.themovielibrary.home.api.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    val movieList: LiveData<List<Movie>> = repository.fetchMovieFromRoom()

    init {
        getAllMovies()
    }

    private fun getAllMovies() = viewModelScope.launch(Dispatchers.IO) {
       if (movieList.value.isNullOrEmpty()) {
            val response = repository.getMoviesFromApi()
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    repository.insert(it)
                }
            } else {
                Log.e("Error:", response.message())
            }
        }
    }

    fun updateFavorite(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateMovie(movie)
    }
}