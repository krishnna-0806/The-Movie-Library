package com.example.themovielibrary.home.api

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.themovielibrary.home.room.MovieRoomDatabase
import retrofit2.Response

class MovieRepository(
    private val apiService: ApiService,
    context: Context
) {

    suspend fun getMoviesFromApi(): Response<MovieDetails> {
        return apiService.getAllMovies()
    }

    private var movieDao = MovieRoomDatabase.getDatabase(context = context).movieDao()

    fun fetchMovieFromRoom(): LiveData<List<Movie>> {
        return movieDao.getMovies()
    }

    suspend fun insert(movie: List<Movie>) {
        movieDao.insert(movie)
    }


    suspend fun updateMovie(movie: Movie) {
        movie.id?.let { movieDao.updateFavorite(it, movie.isFavorite) }
    }
}