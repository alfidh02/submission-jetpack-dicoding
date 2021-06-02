package com.alfidh.tvmov.model.data.remote.response.tv

import com.alfidh.tvmov.model.data.remote.response.genre.GenreResponse
import com.google.gson.annotations.SerializedName

data class TVDetailResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("first_air_date")
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