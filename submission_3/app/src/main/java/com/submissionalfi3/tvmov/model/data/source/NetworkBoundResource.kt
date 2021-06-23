package com.submissionalfi3.tvmov.model.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.submissionalfi3.tvmov.model.data.remote.response.ApiResponse
import com.submissionalfi3.tvmov.model.data.remote.response.StatusResponse
import com.submissionalfi3.tvmov.utilities.AppExecutors
import com.submissionalfi3.tvmov.utilities.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val db = loadFromDb()

        result.addSource(db) { data ->
            result.removeSource(db)
            if (shouldFetch(data)) {
                fetchFromNetwork(db)
            } else {
                result.addSource(db) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    executors.diskIO().execute {
                        saveCallResult(response.body)
                        executors.mainThread().execute {
                            result.addSource(loadFromDb()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                StatusResponse.EMPTY -> executors.mainThread().execute {
                    result.addSource(loadFromDb()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}