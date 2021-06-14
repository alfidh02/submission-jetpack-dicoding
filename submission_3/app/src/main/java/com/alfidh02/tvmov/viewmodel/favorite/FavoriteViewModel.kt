package com.alfidh02.tvmov.viewmodel.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository

class FavoriteViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {

    fun getFavListMovie(): LiveData<PagedList<MovieEntity>> = tvMovieRepository.getMoviesFav()

    fun getFavListTV(): LiveData<PagedList<TVEntity>> = tvMovieRepository.getTVShowsFav()

    fun setFavListMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.addFav
        tvMovieRepository.setMoviesFav(movieEntity, newState)
    }

    fun setFavListTV(tvShowEntity: TVEntity) {
        val newState = !tvShowEntity.addFav
        tvMovieRepository.setTVShowsFav(tvShowEntity, newState)
    }
}