package com.example.themovielibrary.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovielibrary.home.api.MovieDetails
import com.example.themovielibrary.home.api.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel constructor(private val repository: MovieRepository)  : ViewModel() {

    val movieList = MutableLiveData<MovieDetails>()
    val errorMessage = MutableLiveData<String>()

     fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<MovieDetails> {

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}