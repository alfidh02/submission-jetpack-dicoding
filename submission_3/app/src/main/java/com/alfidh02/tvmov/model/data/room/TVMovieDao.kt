package com.alfidh02.tvmov.model.data.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity

@Dao
interface TVMovieDao {

    @Query("SELECT * FROM movie_entity")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entity")
    fun getTV(): DataSource.Factory<Int, TVEntity>

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_entity WHERE id = :id")
    fun getTVById(id: Int): LiveData<TVEntity>

    @Query("SELECT * FROM movie_entity WHERE addFav = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entity WHERE addFav = 1")
    fun getFavTV(): DataSource.Factory<Int, TVEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTV(tvShow: List<TVEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTV(tvShow: TVEntity)
}