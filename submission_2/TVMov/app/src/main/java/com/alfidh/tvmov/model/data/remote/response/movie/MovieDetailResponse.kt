package com.alfidh.tvmov.model.data.remote.response.movie

import com.alfidh.tvmov.model.data.remote.response.genre.GenreResponse
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val pic: String,
    @SerializedName("vote_average")
    val rate: Double,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("overview")
    val desc: String
)