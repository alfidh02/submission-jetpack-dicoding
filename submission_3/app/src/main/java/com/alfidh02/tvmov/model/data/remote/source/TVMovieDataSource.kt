package com.alfidh02.tvmov.model.data.remote.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.vo.Resource

interface TVMovieDataSource {

    fun loadMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun loadDetailMovies(movieID: Int): LiveData<Resource<MovieEntity>>

    fun loadTVShow(): LiveData<Resource<PagedList<TVEntity>>>

    fun loadDetailTVShow(tvShowID: Int): LiveData<Resource<TVEntity>>

    fun setMoviesFav(movie: MovieEntity, state: Boolean)

    fun setTVShowsFav(tv: TVEntity, state: Boolean)

    fun getMoviesFav(): LiveData<PagedList<MovieEntity>>

    fun getTVShowsFav(): LiveData<PagedList<TVEntity>>
}