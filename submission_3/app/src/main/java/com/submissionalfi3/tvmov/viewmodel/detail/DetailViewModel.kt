package com.submissionalfi3.tvmov.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import com.submissionalfi3.tvmov.utilities.vo.Resource

class DetailViewModel(private val filmCatalogueRepository: TVMovieRepository) :
    ViewModel() {

    private lateinit var dataDetail: LiveData<Resource<DetailEntity>>
    private lateinit var dataDetailTVShow: LiveData<Resource<TVEntity>>

    fun setDataMovie(movieId: Int): LiveData<Resource<DetailEntity>> {
        dataDetail = filmCatalogueRepository.getDetailMovie(movieId)
        return dataDetail
    }

    fun setDataTV(tvShowId: Int): LiveData<Resource<DetailEntity>> {
        dataDetail = filmCatalogueRepository.getDetailTV(tvShowId)
        return dataDetail
    }

    fun setMovieFavorite(movieEntity: MovieEntity, newState: Boolean) {
        filmCatalogueRepository.setMoviesFav(movieEntity, newState)
    }

    fun setTVFavorite(tvEntity: TVEntity, newState: Boolean) {
        filmCatalogueRepository.setTVFav(tvEntity, newState)
    }
}