package com.alfidh02.tvmov.model.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.local.entity.MovieEntity
import com.alfidh02.tvmov.model.data.local.entity.TVEntity
import com.alfidh02.tvmov.testutil.vo.Resource

interface TVMovieDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieID: Int): LiveData<Resource<MovieEntity>>

    fun getTV(): LiveData<Resource<PagedList<TVEntity>>>

    fun getDetailTV(tvShowID: Int): LiveData<Resource<TVEntity>>

    fun setMoviesFav(movie: MovieEntity, state: Boolean)

    fun setTVFav(tv: TVEntity, state: Boolean)

    fun getMoviesFav(): LiveData<PagedList<MovieEntity>>

    fun getTVFav(): LiveData<PagedList<TVEntity>>
}