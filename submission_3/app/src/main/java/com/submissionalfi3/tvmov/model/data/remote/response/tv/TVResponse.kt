package com.submissionalfi3.tvmov.model.data.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TVResponse (
    @SerializedName("results")
    val results: List<TVRemote>
)