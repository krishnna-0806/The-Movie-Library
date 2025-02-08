package com.example.themovielibrary.home.api

import com.example.themovielibrary.home.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2OWI1MDM4ZDhmMGRlMWU4OWFlMGE2OGUzMGNmYTU4NSIsIm5iZiI6MTczODk1MDI0NS4xNSwic3ViIjoiNjdhNjQ2NjU5MzdkMmU5YjYwNjZkNTliIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.owHi9EpK4WYX2H7xAL6Rov_SA9_ERuYwzPq69VXK0TA",
        "Accept: application/json"
    )
    @GET("popular?language=en-US&page=1")
    fun getAllMovies(): Call<MovieDetails>

    companion object {

        private var retrofitService: ApiService? = null
        private val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        fun getInstance(): ApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }
}