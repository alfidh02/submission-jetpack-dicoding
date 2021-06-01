package com.alfidh.tvmov.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.alfidh.tvmov.model.data.entity.TVEntity
import com.alfidh.tvmov.model.repository.TVMovieRepository

class TVViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {
//    fun getListTV(): LiveData<List<TVEntity>> = tvMovieRepository.loadMovies()
}