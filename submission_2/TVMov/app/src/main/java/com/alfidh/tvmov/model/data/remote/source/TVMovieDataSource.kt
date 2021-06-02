package com.alfidh.tvmov.model.data.remote.source

import androidx.lifecycle.LiveData
import com.alfidh.tvmov.model.data.entity.DetailEntity
import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.alfidh.tvmov.model.data.entity.TVEntity

interface TVMovieDataSource {

    fun loadMovies(): LiveData<List<MovieEntity>>

    fun loadDetailMovies(movieID: String): LiveData<DetailEntity>

    fun loadTVShow(): LiveData<List<TVEntity>>

    fun loadDetailTVShow(tvShowID: String): LiveData<DetailEntity>

}