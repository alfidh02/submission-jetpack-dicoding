package com.submissionalfi3.tvmov.model.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.utilities.vo.Resource

interface TVMovieDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieID: Int): LiveData<Resource<DetailEntity>>

    fun getTV(): LiveData<Resource<PagedList<TVEntity>>>

    fun getDetailTV(tvShowID: Int): LiveData<Resource<DetailEntity>>

    fun setMoviesFav(movie: MovieEntity, state: Boolean)

    fun setTVFav(tv: TVEntity, state: Boolean)

    fun getMoviesFav(): LiveData<PagedList<MovieEntity>>

    fun getTVFav(): LiveData<PagedList<TVEntity>>
}