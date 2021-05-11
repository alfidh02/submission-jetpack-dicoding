package com.project.alfidh02.jetpack.tvmov.viewmodel.tv

import androidx.lifecycle.ViewModel
import com.project.alfidh02.jetpack.tvmov.model.data.MovieTVEntity
import com.project.alfidh02.jetpack.tvmov.model.utils.MovieTVDummy

class TvViewModel : ViewModel() {

    fun getTV(): List<MovieTVEntity> = MovieTVDummy.generateTVShows()

}