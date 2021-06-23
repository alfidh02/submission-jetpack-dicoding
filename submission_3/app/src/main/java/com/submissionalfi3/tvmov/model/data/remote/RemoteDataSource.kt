package com.submissionalfi3.tvmov.model.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.submissionalfi3.tvmov.model.data.remote.response.ApiResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieRemoteResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVRemoteResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVResponse
import com.submissionalfi3.tvmov.model.data.remote.network.ApiConfig
import com.submissionalfi3.tvmov.utilities.EspressoIdlingResource
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

    fun getTopMovies(): LiveData<ApiResponse<List<MovieRemoteResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieRemoteResponse>>>()

        ApiConfig.getApiService().getTopMovies(1)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    resultMovie.value = ApiResponse.success(response.body()?.results as List<MovieRemoteResponse>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }

            })
        return resultMovie
    }

    fun getDetailMovies(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieDetailResponse>>()

        ApiConfig.getApiService().getDetailMovies(movieId)
            .enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    result.value = ApiResponse.success(response.body() as MovieDetailResponse)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }


    fun getTopTV(): LiveData<ApiResponse<List<TVRemoteResponse>>> {
        EspressoIdlingResource.increment()
        val resultTV = MutableLiveData<ApiResponse<List<TVRemoteResponse>>>()

        ApiConfig.getApiService().getTopTVShows(1)
            .enqueue(object : Callback<TVResponse> {
                override fun onResponse(
                    call: Call<TVResponse>,
                    response: Response<TVResponse>
                ) {
                    resultTV.value =
                        ApiResponse.success(response.body()?.results as List<TVRemoteResponse>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }

            })
        return resultTV
    }

    fun getDetailTV(tvId: Int): LiveData<ApiResponse<TVDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TVDetailResponse>>()

        ApiConfig.getApiService().getDetailTVShows(tvId)
            .enqueue(object : Callback<TVDetailResponse> {
                override fun onResponse(
                    call: Call<TVDetailResponse>,
                    response: Response<TVDetailResponse>
                ) {
                    result.value = ApiResponse.success(response.body() as TVDetailResponse)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TVDetailResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }

}