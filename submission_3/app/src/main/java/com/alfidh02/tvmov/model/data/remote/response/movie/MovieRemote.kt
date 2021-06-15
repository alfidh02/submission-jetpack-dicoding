package com.alfidh02.tvmov.model.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieRemote (

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("release_date")
    val date: String,

    @SerializedName("poster_path")
    val image: String,

    @SerializedName("vote_average")
    val rate: Double,

    @SerializedName("overview")
    val desc: String
)