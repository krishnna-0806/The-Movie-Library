package com.example.themovielibrary.home.api

class MovieRepository constructor(private val retrofitService: ApiService) {
     fun getAllMovies() = retrofitService.getAllMovies()
}