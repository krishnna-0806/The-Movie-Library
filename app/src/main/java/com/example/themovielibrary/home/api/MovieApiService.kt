package com.example.themovielibrary.home.api

import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieApiService {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2OWI1MDM4ZDhmMGRlMWU4OWFlMGE2OGUzMGNmYTU4NSIsIm5iZiI6MTczODk1MDI0NS4xNSwic3ViIjoiNjdhNjQ2NjU5MzdkMmU5YjYwNjZkNTliIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.owHi9EpK4WYX2H7xAL6Rov_SA9_ERuYwzPq69VXK0TA",
        "Accept: application/json"
    )
    @GET("popular?language=en-US&page=1")
    suspend fun getAllMovies(): retrofit2.Response<MovieDetails>

}