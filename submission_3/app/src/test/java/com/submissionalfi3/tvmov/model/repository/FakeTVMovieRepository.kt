package com.submissionalfi3.tvmov.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.LocalDataSource
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.model.data.remote.RemoteDataSource
import com.submissionalfi3.tvmov.model.data.remote.response.ApiResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieRemoteResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVRemoteResponse
import com.submissionalfi3.tvmov.model.data.source.NetworkBoundResource
import com.submissionalfi3.tvmov.model.data.source.TVMovieDataSource
import com.submissionalfi3.tvmov.utilities.AppExecutors
import com.submissionalfi3.tvmov.utilities.vo.Resource

class FakeTVMovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : TVMovieDataSource {

    companion object {
        @Volatile
        private var instance: FakeTVMovieRepository? = null

        fun getInstance(
            remote: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): FakeTVMovieRepository =
            instance ?: synchronized(this) {
                FakeTVMovieRepository(remote, localDataSource, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieRemoteResponse>>(appExecutors) {

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), conf).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieRemoteResponse>>> =
                remoteDataSource.getTopMovies()

            override fun saveCallResult(data: List<MovieRemoteResponse>) {
                val listMovie = ArrayList<MovieEntity>()
                for (dataMovie in data) {
                    dataMovie.apply {
                        val movie = MovieEntity(
                            id,
                            title,
                            date,
                            image,
                            rate,
                            "",
                            false
                        )
                        listMovie.add(movie)
                    }
                }
                localDataSource.insertMovie(listMovie)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieID: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundResource<DetailEntity, MovieDetailResponse>(appExecutors) {

            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null

            override fun loadFromDb(): LiveData<DetailEntity> =
                localDataSource.getMovieById(movieID)

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getDetailMovies(movieID)

            override fun saveCallResult(data: MovieDetailResponse) {
                with(data) {
                    val listGenreDetail = ArrayList<String>()
                    for (genre in genres) {
                        listGenreDetail.add(genre.name)
                    }

                    val dataDetailMovie = DetailEntity(
                        id = id,
                        title = title,
                        date = date,
                        image = image,
                        rate = rate,
                        desc = desc,
                        genres = listGenreDetail
                    )
                    localDataSource.insertDetailTVMovie(dataDetailMovie)
                }
            }
        }.asLiveData()
    }

    override fun getTV(): LiveData<Resource<PagedList<TVEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TVEntity>, List<TVRemoteResponse>>(appExecutors) {

            override fun shouldFetch(data: PagedList<TVEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<PagedList<TVEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTV(), conf).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<TVRemoteResponse>>> =
                remoteDataSource.getTopTV()

            override fun saveCallResult(data: List<TVRemoteResponse>) {
                val listTVShow = ArrayList<TVEntity>()
                for (dataTVShow in data) {
                    with(dataTVShow) {
                        val tvShow = TVEntity(
                            id,
                            title,
                            date,
                            image,
                            rate,
                            "",
                            false
                        )
                        listTVShow.add(tvShow)
                    }
                }
                localDataSource.insertTV(listTVShow)
            }
        }.asLiveData()
    }

    override fun getDetailTV(tvShowID: Int): LiveData<Resource<TVEntity>> {
        return object : NetworkBoundResource<TVEntity, TVDetailResponse>(appExecutors) {

            override fun shouldFetch(data: TVEntity?): Boolean = data == null

            override fun loadFromDb(): LiveData<TVEntity> =
                localDataSource.getTVById(tvShowID)

            override fun createCall(): LiveData<ApiResponse<TVDetailResponse>> =
                remoteDataSource.getDetailTV(tvShowID)

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
        localDataSource.setMovieFav(movie, state)

    override fun setTVFav(tv: TVEntity, state: Boolean) =
        localDataSource.setTVFav(tv, state)

    override fun getMoviesFav(): LiveData<PagedList<MovieEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getMoviesFav(), conf).build()
    }

    override fun getTVFav(): LiveData<PagedList<TVEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getTVFav(), conf).build()
    }
}