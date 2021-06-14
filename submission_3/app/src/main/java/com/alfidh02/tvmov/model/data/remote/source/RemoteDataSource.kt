package com.alfidh02.tvmov.model.data.remote.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alfidh02.tvmov.model.data.remote.response.ApiResponse
import com.alfidh02.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.alfidh02.tvmov.model.data.remote.response.movie.MovieRemote
import com.alfidh02.tvmov.model.data.remote.response.movie.MovieResponse
import com.alfidh02.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.alfidh02.tvmov.model.data.remote.response.tv.TVRemote
import com.alfidh02.tvmov.model.data.remote.response.tv.TVResponse
import com.alfidh02.tvmov.model.network.ApiConfig
import com.alfidh02.tvmov.util.EspressoIdlingResource
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

    fun getTopMovies(): LiveData<ApiResponse<List<MovieRemote>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieRemote>>>()
        ApiConfig.getApiService().getTopMovies(1)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    result.value = ApiResponse.success(response.body()?.result as List<MovieRemote>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }

    fun getDetailMovies(movieId: String): LiveData<ApiResponse<MovieDetailResponse>> {
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


    fun getTopTV(): LiveData<ApiResponse<List<TVRemote>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TVRemote>>>()
        ApiConfig.getApiService().getTopTVShows(1)
            .enqueue(object : Callback<TVResponse> {
                override fun onResponse(
                    call: Call<TVResponse>,
                    response: Response<TVResponse>
                ) {
                    result.value =
                        ApiResponse.success(response.body()?.result as List<TVRemote>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }

            })
        return result
    }

    fun getDetailTV(id: String): LiveData<ApiResponse<TVDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TVDetailResponse>>()
        ApiConfig.getApiService().getDetailTVShows(id)
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