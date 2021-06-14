package com.alfidh02.tvmov.model.data.remote.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.dapoidev.moviecatalogue.model.data.room.TVMovieDao

class LocalDataSource private constructor(private val filmDao: TVMovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: TVMovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao).apply {
                INSTANCE = this
            }
    }

    fun getAllMovie(): DataSource.Factory<Int, MovieEntity> =
        filmDao.getMovies()

    fun getAllTV(): DataSource.Factory<Int, TVEntity> =
        filmDao.getTV()

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = filmDao.getFavMovies()

    fun getFavTV(): DataSource.Factory<Int, TVEntity> = filmDao.getFavTV()

    fun getMovieById(id: Int): LiveData<MovieEntity> = filmDao.getMovieById(id)

    fun getTVById(id: Int): LiveData<TVEntity> = filmDao.getTVById(id)

    fun insertMovie(movies: List<MovieEntity>) = filmDao.insertMovie(movies)

    fun insertTV(tv: List<TVEntity>) = filmDao.insertTV(tv)

    fun updateFavMovie(movies: MovieEntity, newState: Boolean) {
        movies.addFav = newState
        filmDao.updateMovie(movies)
    }

    fun updateFavTV(tv: TVEntity, newState: Boolean) {
        tv.addFav = newState
        filmDao.updateTV(tv)
    }
}