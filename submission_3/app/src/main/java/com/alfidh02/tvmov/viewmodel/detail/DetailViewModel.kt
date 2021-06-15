package com.alfidh02.tvmov.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alfidh02.tvmov.model.data.local.entity.MovieEntity
import com.alfidh02.tvmov.model.data.local.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.testutil.vo.Resource

class DetailViewModel(private val filmCatalogueRepository: TVMovieRepository) :
    ViewModel() {

    private lateinit var dataDetailMovie: LiveData<Resource<MovieEntity>>
    private lateinit var dataDetailTVShow: LiveData<Resource<TVEntity>>

    fun setDataMovie(movieId: Int) : LiveData<Resource<MovieEntity>> {
        dataDetailMovie = filmCatalogueRepository.getDetailMovie(movieId)
        return dataDetailMovie
    }

    fun setDataTV(tvShowId: Int) : LiveData<Resource<TVEntity>> {
        dataDetailTVShow = filmCatalogueRepository.getDetailTV(tvShowId)
        return dataDetailTVShow
    }

    fun setMovieFavorite() {
        val movie = dataDetailMovie.value
        if (movie?.data != null) {
            val newState = !movie.data.favorite
            filmCatalogueRepository.setMoviesFav(movie.data, newState)
        }
    }

    fun setTVFavorite() {
        val tvShow = dataDetailTVShow.value
        if (tvShow?.data != null) {
            val newState = !tvShow.data.favorite
            filmCatalogueRepository.setTVFav(tvShow.data, newState)
        }
    }
}