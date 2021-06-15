package com.submissionalfi3.tvmov.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entity.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entity.TVEntity
import com.submissionalfi3.tvmov.model.data.remote.response.ApiResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieRemote
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVRemote
import com.submissionalfi3.tvmov.model.data.local.LocalDataSource
import com.submissionalfi3.tvmov.model.data.source.NetworkBoundResource
import com.submissionalfi3.tvmov.model.data.remote.RemoteDataSource
import com.submissionalfi3.tvmov.model.data.source.TVMovieDataSource
import com.submissionalfi3.tvmov.testutil.AppExecutors
import com.submissionalfi3.tvmov.testutil.vo.Resource

class TVMovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : TVMovieDataSource {

    companion object {
        @Volatile
        private var instance: TVMovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): TVMovieRepository =
            instance ?: synchronized(this) {
                TVMovieRepository(remoteDataSource, localDataSource, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieRemote>>(appExecutors) {

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieRemote>>> =
                remoteDataSource.getTopMovies()

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<MovieRemote>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    response.apply {
                        val movie = MovieEntity(
                            id,
                            title,
                            date,
                            image,
                            rate,
                            desc,
                            false
                        )
                        movieList.add(movie)
                    }
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieID: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {

            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(movieID)

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getDetailMovies(movieID)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun saveCallResult(data: MovieDetailResponse) {
                with(data) {
                    val dataDetailMovie = MovieEntity(
                        id = id,
                        title = title,
                        date = date,
                        image = image,
                        rate = rate,
                        desc = desc,
                        favorite = false
                    )
                    localDataSource.setMovieFav(dataDetailMovie, false)
                }
            }
        }.asLiveData()
    }

    override fun getTV(): LiveData<Resource<PagedList<TVEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TVEntity>, List<TVRemote>>(appExecutors) {

            override fun shouldFetch(data: PagedList<TVEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<PagedList<TVEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTV(), config).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<TVRemote>>> =
                remoteDataSource.getTopTV()

            override fun saveCallResult(data: List<TVRemote>) {
                val listTVShow = ArrayList<TVEntity>()
                for (dataTVShow in data) {
                    with(dataTVShow) {
                        val tvShow = TVEntity(
                            id,
                            title,
                            date,
                            image,
                            rate,
                            desc,
                            false)
                        listTVShow.add(tvShow)
                    }
                }
                localDataSource.insertTV(listTVShow)
            }
        }.asLiveData()
    }

    override fun getDetailTV(tvShowID: Int): LiveData<Resource<TVEntity>> {
        return object : NetworkBoundResource<TVEntity, TVDetailResponse>(appExecutors) {

            override fun loadFromDb(): LiveData<TVEntity> =
                localDataSource.getTVById(tvShowID)

            override fun createCall(): LiveData<ApiResponse<TVDetailResponse>> =
                remoteDataSource.getDetailTV(tvShowID)

            override fun shouldFetch(data: TVEntity?): Boolean = data == null

            override fun saveCallResult(data: TVDetailResponse) {
                with(data) {
                    val dataDetailTVShow = TVEntity(
                        id = id,
                        title = title,
                        date = date,
                        image = image,
                        rate = rate,
                        desc = desc,
                        favorite = false
                    )
                    localDataSource.setTVFav(dataDetailTVShow, false)
                }
            }
        }.asLiveData()
    }

    override fun setMoviesFav(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFav(movie, state) }

    override fun setTVFav(tv: TVEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTVFav(tv, state) }

    override fun getMoviesFav(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getMoviesFav(), config).build()
    }

    override fun getTVFav(): LiveData<PagedList<TVEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getTVFav(), config).build()
    }
}