package com.project.alfidh02.jetpack.tvmov.viewmodel.detail

import androidx.lifecycle.ViewModel
import com.project.alfidh02.jetpack.tvmov.model.data.MovieTVEntity
import com.project.alfidh02.jetpack.tvmov.model.utils.MovieTVDummy

class DetailViewModel : ViewModel() {
    private lateinit var title: String

    fun setSelectedMovieTv(title: String) {
        this.title = title
    }

    fun getSelectedMovieTv() : MovieTVEntity {
        lateinit var movieTVEntity: MovieTVEntity

        val moviesModel = MovieTVDummy.generateMovies()
        for (movieModel in moviesModel) {
            if (movieModel.title == title) movieTVEntity = movieModel
        }

        val tvShowsModel = MovieTVDummy.generateTVShows()
        for (tvShowModel in tvShowsModel) {
            if (tvShowModel.title == title) movieTVEntity = tvShowModel
        }
        return movieTVEntity
    }
}