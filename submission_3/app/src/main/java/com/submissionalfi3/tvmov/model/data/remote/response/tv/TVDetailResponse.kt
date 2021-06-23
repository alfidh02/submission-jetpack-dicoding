package com.submissionalfi3.tvmov.model.data.remote.response.tv

import com.google.gson.annotations.SerializedName
import com.submissionalfi3.tvmov.model.data.remote.response.genre.GenreResponse

data class TVDetailResponse (

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val title: String,

    @SerializedName("first_air_date")
    val date: String,

    @SerializedName("poster_path")
    val image: String,

    @SerializedName("vote_average")
    val rate: Double,

    @SerializedName("overview")
    val desc: String,

    @SerializedName("genres")
    val genres: List<GenreResponse>
)