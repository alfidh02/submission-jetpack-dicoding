package com.alfidh02.tvmov.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.vo.Resource

class DetailViewModel(private val filmCatalogueRepository: TVMovieRepository) :
    ViewModel() {

    private lateinit var dataDetailMovie: LiveData<Resource<MovieEntity>>
    private lateinit var dataDetailTVShow: LiveData<Resource<TVEntity>>

    fun setDataMovie(movieId: Int) : LiveData<Resource<MovieEntity>> {
        dataDetailMovie = filmCatalogueRepository.loadDetailMovies(movieId)
        return dataDetailMovie
    }

    fun setDataTV(tvShowId: Int) : LiveData<Resource<TVEntity>> {
        dataDetailTVShow = filmCatalogueRepository.loadDetailTVShow(tvShowId)
        return dataDetailTVShow
    }

    fun setMovieFavorite() {
        val dataMovie = dataDetailMovie.value
        if (dataMovie?.data != null) {
            val newState = !dataMovie.data.addFav
            filmCatalogueRepository.setMoviesFav(dataMovie.data, newState)
        }
    }

    fun setTVFavorite() {
        val dataTVShow = dataDetailTVShow.value
        if (dataTVShow?.data != null) {
            val newState = !dataTVShow.data.addFav
            filmCatalogueRepository.setTVShowsFav(dataTVShow.data, newState)
        }
    }
}