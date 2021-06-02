package com.alfidh.tvmov.model.data.remote.source

import com.alfidh.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.alfidh.tvmov.model.data.remote.response.movie.MovieRemote
import com.alfidh.tvmov.model.data.remote.response.movie.MovieResponse
import com.alfidh.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.alfidh.tvmov.model.data.remote.response.tv.TVRemote
import com.alfidh.tvmov.model.data.remote.response.tv.TVResponse
import com.alfidh.tvmov.model.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getTopMovies(callback: LoadMovieCallback) {
//        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getTopMovies(1)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    callback.onAllMoviesReceived(response.body()?.result)
//                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getDetailMovies(callback: LoadDetailMovieCallback, id: String) {
//        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailMovies(id)
            .enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    callback.onAllDetailMoviesReceived(response.body())
//                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
//                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getTopTV(callback: LoadTVCallback) {
//        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getTopTVShows(1)
            .enqueue(object : Callback<TVResponse> {
                override fun onResponse(
                    call: Call<TVResponse>,
                    response: Response<TVResponse>
                ) {
                    callback.onAllTVShowsReceived(response.body()?.result)
//                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TVResponse>, t: Throwable) {
//                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getDetailTV(callback: LoadDetailTVCallback, id: String) {
//        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailTVShows(id)
            .enqueue(object : Callback<TVDetailResponse> {
                override fun onResponse(
                    call: Call<TVDetailResponse>,
                    response: Response<TVDetailResponse>
                ) {
                    callback.onAllDetailTVShowsReceived(response.body())
//                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TVDetailResponse>, t: Throwable) {
//                    EspressoIdlingResource.decrement()
                }

            })
    }


    interface LoadMovieCallback {
        fun onAllMoviesReceived(moviesResponse: List<MovieRemote>?)
    }

    interface LoadDetailMovieCallback {
        fun onAllDetailMoviesReceived(moviesDetail: MovieDetailResponse?)
    }

    interface LoadTVCallback {
        fun onAllTVShowsReceived(tvResponse: List<TVRemote>?)
    }

    interface LoadDetailTVCallback {
        fun onAllDetailTVShowsReceived(tvShowsDetail: TVDetailResponse?)
    }
}