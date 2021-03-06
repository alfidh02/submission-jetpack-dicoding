package com.submissionalfi3.tvmov.model.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.model.data.local.room.TVMovieDao

class LocalDataSource private constructor(private val tvMovieDao: TVMovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(tvMovieDao: TVMovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(tvMovieDao).apply { INSTANCE = this }
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = tvMovieDao.getMovies()
    fun getMoviesFav(): DataSource.Factory<Int, MovieEntity> = tvMovieDao.getMoviesFav()
    fun getMovieById(id: Int): LiveData<DetailEntity> = tvMovieDao.getMovieById(id)
    fun insertMovie(movies: List<MovieEntity>) = tvMovieDao.insertMovie(movies)
    fun setMovieFav(movies: MovieEntity, newState: Boolean) {
        movies.favorite = newState
        tvMovieDao.updateMovie(movies)
    }

    fun getTV(): DataSource.Factory<Int, TVEntity> = tvMovieDao.getTV()
    fun getTVFav(): DataSource.Factory<Int, TVEntity> = tvMovieDao.getTVFav()
    fun getTVById(id: Int): LiveData<DetailEntity> = tvMovieDao.getTVById(id)
    fun insertTV(tv: List<TVEntity>) = tvMovieDao.insertTV(tv)
    fun setTVFav(tv: TVEntity, newState: Boolean) {
        tv.favorite = newState
        tvMovieDao.updateTV(tv)
    }

    fun insertDetailTVMovie(detail: DetailEntity) = tvMovieDao.insertDetailTVMovie(detail)
}