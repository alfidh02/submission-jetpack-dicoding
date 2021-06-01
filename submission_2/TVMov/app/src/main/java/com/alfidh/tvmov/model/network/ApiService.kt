package com.alfidh.tvmov.model.network

import com.alfidh.tvmov.BuildConfig
import com.alfidh.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.alfidh.tvmov.model.data.remote.response.movie.MovieResponse
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

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getDetailMovies(
        @Path("movie_id") id: String
    ): Call<MovieDetailResponse>
//
//    @GET("tv/popular?api_key=$API_KEY")
//    fun getTopTVShows(
//        @Query("page") page: Int
//    ): Call<TVShowsResponse>
//
//    @GET("tv/{tv_id}?api_key=$API_KEY")
//    fun getDetailTVShows(
//        @Path("tv_id") id: String
//    ): Call<TVShowsDetailResponse>
}