package com.alfidh.tvmov.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alfidh.tvmov.model.data.entity.DetailEntity
import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.alfidh.tvmov.model.data.entity.TVEntity
import com.alfidh.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.alfidh.tvmov.model.data.remote.response.movie.MovieRemote
import com.alfidh.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.alfidh.tvmov.model.data.remote.response.tv.TVRemote
import com.alfidh.tvmov.model.data.remote.source.RemoteDataSource
import com.alfidh.tvmov.model.data.remote.source.TVMovieDataSource

class TVMovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    TVMovieDataSource {

    companion object {
        @Volatile
        private var instance: TVMovieRepository? = null

        fun getInstance(remote: RemoteDataSource): TVMovieRepository =
            instance ?: synchronized(this) {
                TVMovieRepository(remote).apply { instance = this }
            }
    }

    override fun loadMovies(): LiveData<List<MovieEntity>> {
        val getMovie = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getTopMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMoviesReceived(moviesResponse: List<MovieRemote>?) {
                val listMovie = ArrayList<MovieEntity>()
                if (moviesResponse != null) {
                    for (movies in moviesResponse) {
                        movies.apply {
                            val movie = MovieEntity(id, title, date, pic, rate)
                            listMovie.add(movie)
                        }
                    }
                    getMovie.postValue(listMovie)
                }
            }
        })
        return getMovie
    }

    override fun loadDetailMovies(movieID: String): LiveData<DetailEntity> {
        val getDetailMovie = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailMovies(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onAllDetailMoviesReceived(moviesDetail: MovieDetailResponse?) {
                lateinit var detailMovie: DetailEntity
                moviesDetail?.apply {

                    val listGenreDetail = ArrayList<String>()
                    for (genre in genres) {
                        listGenreDetail.add(genre.name)
                    }

                    detailMovie = DetailEntity(
                        id = id,
                        title = title,
                        date = date,
                        rate = rate,
                        genres = listGenreDetail,
                        pic = pic,
                        desc = desc
                    )
                    getDetailMovie.postValue(detailMovie)
                }
            }
        }, movieID)
        return getDetailMovie
    }

    override fun loadTVShow(): LiveData<List<TVEntity>> {
        val getTV = MutableLiveData<List<TVEntity>>()

        remoteDataSource.getTopTV(object : RemoteDataSource.LoadTVCallback {
            override fun onAllTVShowsReceived(tvResponse: List<TVRemote>?) {
                val listTV = ArrayList<TVEntity>()
                if (tvResponse != null) {
                    for (tvShow in tvResponse) {
                        tvShow.apply {
                            val tv = TVEntity(id, title, date, pic, rate)
                            listTV.add(tv)
                        }
                    }
                    getTV.postValue(listTV)
                }
            }
        })
        return getTV
    }

    override fun loadDetailTVShow(tvShowID: String): LiveData<DetailEntity> {
        val getDetailTVShow = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailTV(object : RemoteDataSource.LoadDetailTVCallback {
            override fun onAllDetailTVShowsReceived(tvShowsDetail: TVDetailResponse?) {
                lateinit var detailTV: DetailEntity
                tvShowsDetail?.apply {

                    val listGenreDetail = ArrayList<String>()
                    for (genre in genres) {
                        listGenreDetail.add(genre.name)
                    }

                    detailTV = DetailEntity(
                        id = id,
                        title = title,
                        date = date,
                        rate = rate,
                        genres = listGenreDetail,
                        pic = pic,
                        desc = desc
                    )
                    getDetailTVShow.postValue(detailTV)
                }
            }
        }, tvShowID)
        return getDetailTVShow
    }
}