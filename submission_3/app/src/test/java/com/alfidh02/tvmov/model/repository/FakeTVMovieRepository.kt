package com.alfidh02.tvmov.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.data.remote.response.ApiResponse
import com.alfidh02.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.alfidh02.tvmov.model.data.remote.response.movie.MovieRemote
import com.alfidh02.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.alfidh02.tvmov.model.data.remote.response.tv.TVRemote
import com.alfidh02.tvmov.model.data.remote.source.LocalDataSource
import com.alfidh02.tvmov.model.data.remote.source.NetworkBoundResource
import com.alfidh02.tvmov.model.data.remote.source.RemoteDataSource
import com.alfidh02.tvmov.model.data.remote.source.TVMovieDataSource
import com.alfidh02.tvmov.util.AppExecutors
import com.alfidh02.tvmov.vo.Resource

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

    override fun loadMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieRemote>>(appExecutors) {

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), conf).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieRemote>>> =
                remoteDataSource.getTopMovies()

            override fun saveCallResult(data: List<MovieRemote>) {
                val listMovie = ArrayList<MovieEntity>()
                for (dataMovie in data) {
                    dataMovie.apply {
                        val movie = MovieEntity(
                            id, title, date, image, rate, desc
                        )
                        listMovie.add(movie)
                    }
                }
                localDataSource.insertMovie(listMovie)
            }
        }.asLiveData()
    }

    override fun loadDetailMovies(movieID: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(movieID)
            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getDetailMovies(movieID.toString())

            override fun saveCallResult(data: MovieDetailResponse) {
                with(data) {
                    val dataDetailMovie = MovieEntity(
                        id = id,
                        title = title,
                        date = date,
                        image = image,
                        rate = rate,
                        desc = desc,
                        addFav = false
                    )
                    localDataSource.updateFavMovie(dataDetailMovie, false)
                }
            }
        }.asLiveData()
    }

    override fun loadTVShow(): LiveData<Resource<PagedList<TVEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TVEntity>, List<TVRemote>>(appExecutors) {

            override fun shouldFetch(data: PagedList<TVEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<PagedList<TVEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTV(), conf).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<TVRemote>>> =
                remoteDataSource.getTopTV()

            override fun saveCallResult(data: List<TVRemote>) {
                val listTVShow = ArrayList<TVEntity>()
                for (dataTVShow in data) {
                    with(dataTVShow) {
                        val tvShow = com.alfidh02.tvmov.model.data.entity.TVEntity(
                            id,
                            title,
                            date,
                            desc,
                            rate,
                            image,
                            false
                        )
                        listTVShow.add(tvShow)
                    }
                }
                localDataSource.insertTV(listTVShow)
            }
        }.asLiveData()
    }

    override fun loadDetailTVShow(tvShowID: Int): LiveData<Resource<TVEntity>> {
        return object : NetworkBoundResource<TVEntity, TVDetailResponse>(appExecutors) {

            override fun shouldFetch(data: TVEntity?): Boolean = data == null

            override fun loadFromDb(): LiveData<TVEntity> =
                localDataSource.getTVById(tvShowID)

            override fun createCall(): LiveData<ApiResponse<TVDetailResponse>> =
                remoteDataSource.getDetailTV(tvShowID.toString())

            override fun saveCallResult(data: TVDetailResponse) {
                with(data) {
                    val dataDetailTVShow = TVEntity(
                        id = id,
                        title = title,
                        date = date,
                        image = image,
                        rate = rate,
                        desc = desc,
                        addFav = false
                    )
                    localDataSource.updateFavTV(dataDetailTVShow, false)
                }
            }
        }.asLiveData()
    }

    override fun setMoviesFav(movie: MovieEntity, state: Boolean) =
        localDataSource.updateFavMovie(movie, state)

    override fun setTVShowsFav(tv: TVEntity, state: Boolean) =
        localDataSource.updateFavTV(tv, state)

    override fun getMoviesFav(): LiveData<PagedList<MovieEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavMovies(), conf).build()
    }

    override fun getTVShowsFav(): LiveData<PagedList<TVEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavTV(), conf).build()
    }
}