package com.submissionalfi3.tvmov.viewmodel.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entity.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entity.TVEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository

class FavoriteViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = tvMovieRepository.getMoviesFav()

    fun getFavoriteTVShow(): LiveData<PagedList<TVEntity>> = tvMovieRepository.getTVFav()
}