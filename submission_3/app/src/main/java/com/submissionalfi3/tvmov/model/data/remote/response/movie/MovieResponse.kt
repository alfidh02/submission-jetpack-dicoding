package com.submissionalfi3.tvmov.model.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results")
    val results: List<MovieRemoteResponse>
)
