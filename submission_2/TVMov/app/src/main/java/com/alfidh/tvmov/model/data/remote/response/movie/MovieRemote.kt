package com.alfidh.tvmov.model.data.remote.response.movie

import com.alfidh.tvmov.model.data.remote.response.genre.GenreResponse
import com.google.gson.annotations.SerializedName

data class MovieRemote (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val pic: String,
    @SerializedName("vote_average")
    val rate: Double
)