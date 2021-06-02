package com.alfidh.tvmov.model.data.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TVRemote (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("poster_path")
    val pic: String,
    @SerializedName("vote_average")
    val rate: Double
)