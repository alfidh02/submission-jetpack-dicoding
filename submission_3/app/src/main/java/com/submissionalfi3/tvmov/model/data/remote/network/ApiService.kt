package com.submissionalfi3.tvmov.model.data.remote.network

import com.submissionalfi3.tvmov.BuildConfig
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("movie/top_rated?api_key=$API_KEY")
    fun getTopMovies(
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("tv/top_rated?api_key=$API_KEY")
    fun getTopTVShows(
        @Query("page") page: Int
    ): Call<TVResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getDetailMovies(
        @Path("movie_id") id: Int
    ): Call<MovieDetailResponse>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getDetailTVShows(
        @Path("tv_id") id: Int
    ): Call<TVDetailResponse>
}