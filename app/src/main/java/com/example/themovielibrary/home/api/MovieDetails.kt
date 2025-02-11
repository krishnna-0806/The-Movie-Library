package com.example.themovielibrary.home.api

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDetails(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<Movie> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)

@Keep
@Entity(tableName = "movies")
data class Movie(
    @ColumnInfo("adult")
    var adult: Boolean? = null,
    @ColumnInfo("backdrop_path")
    var backdropPath: String? = null,
//    @ColumnInfo("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @PrimaryKey
    @ColumnInfo("id") var id: Int? = null,
    @SerializedName("original_language")
    @ColumnInfo("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    @ColumnInfo("original_title")
    var originalTitle: String? = null,
    @ColumnInfo("overview")
    var overview: String? = null,
    @ColumnInfo("popularity")
    var popularity: Double? = null,
    @SerializedName("poster_path")
    @ColumnInfo("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    @ColumnInfo("release_date")
    var releaseDate: String? = null,
    @ColumnInfo("title")
    var title: String? = null,
    @ColumnInfo("video")
    var video: Boolean? = null,
    @ColumnInfo("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    @ColumnInfo("vote_count")
    var voteCount: Int? = null,
    @ColumnInfo("is_favorite")
    var isFavorite: Boolean = false
)