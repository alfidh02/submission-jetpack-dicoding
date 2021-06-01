package com.alfidh.tvmov.model.data.entity

import com.google.gson.annotations.SerializedName

data class TVEntity (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("poster_path")
    val pic: String
)