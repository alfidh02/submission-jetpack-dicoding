package com.submissionalfi3.tvmov.model.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity

@Dao
interface TVMovieDao {

    @Query("SELECT * FROM table_movie")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM table_tv")
    fun getTV(): DataSource.Factory<Int, TVEntity>

    @Query("SELECT * FROM table_detail WHERE id = :id")
    fun getMovieById(id: Int): LiveData<DetailEntity>

    @Query("SELECT * FROM table_detail WHERE id = :id")
    fun getTVById(id: Int): LiveData<DetailEntity>

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTVMovie(detail: DetailEntity)
}