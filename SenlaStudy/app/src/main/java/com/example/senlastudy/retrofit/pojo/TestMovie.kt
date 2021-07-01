package com.example.senlastudy.retrofit.pojo

import android.provider.SyncStateContract
import com.example.senlastudy.utils.Constants
import com.google.gson.annotations.SerializedName

data class TestMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: String,
    @SerializedName("vote_average")
    val voteAverage: String,
    @SerializedName("vote_count")
    val voteCount: String
) {
    val image get() = Constants.BASE_IMAGE_URL + backdropPath
}
