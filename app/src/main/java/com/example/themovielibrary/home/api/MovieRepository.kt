package com.example.themovielibrary.home.api

import androidx.lifecycle.LiveData
import com.example.themovielibrary.home.room.MovieDao
import com.example.themovielibrary.home.room.MovieDatabase
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository  @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) {

    suspend fun getMoviesFromApi(): Response<MovieDetails> {
        return apiService.getAllMovies()
    }

//    private var movieDao = MovieDatabase().movieDao()

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