package com.project.alfidh02.jetpack.tvmov.view.movies

import androidx.lifecycle.ViewModel
import com.project.alfidh02.jetpack.tvmov.model.data.MovieTVEntity
import com.project.alfidh02.jetpack.tvmov.model.utils.MovieTVDummy

class MoviesViewModel : ViewModel() {

    fun getMovie(): List<MovieTVEntity> = MovieTVDummy.generateMovies()

}