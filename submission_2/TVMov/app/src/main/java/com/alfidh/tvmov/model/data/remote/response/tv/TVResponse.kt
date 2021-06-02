package com.alfidh.tvmov.model.data.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TVResponse (
    @SerializedName("results")
    val result: List<TVRemote>
)