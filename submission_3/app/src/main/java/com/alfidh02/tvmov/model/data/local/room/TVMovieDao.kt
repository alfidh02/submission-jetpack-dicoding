package com.alfidh02.tvmov.model.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alfidh02.tvmov.model.data.local.entity.MovieEntity
import com.alfidh02.tvmov.model.data.local.entity.TVEntity

@Dao
interface TVMovieDao {

    @Query("SELECT * FROM table_movie")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM table_tv")
    fun getTV(): DataSource.Factory<Int, TVEntity>

    @Query("SELECT * FROM table_movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM table_tv WHERE id = :id")
    fun getTVById(id: Int): LiveData<TVEntity>

    @Query("SELECT * FROM table_movie WHERE favorite = 1")
    fun getMoviesFav(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM table_tv WHERE favorite = 1")
    fun getTVFav(): DataSource.Factory<Int, TVEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTV(tv: List<TVEntity>)

    @Update
    fun updateTV(tv: TVEntity)
}