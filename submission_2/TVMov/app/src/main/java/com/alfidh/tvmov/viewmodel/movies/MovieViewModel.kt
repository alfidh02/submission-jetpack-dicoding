package com.alfidh.tvmov.viewmodel.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.alfidh.tvmov.model.repository.TVMovieRepository

class MovieViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {
    fun getListMovie(): LiveData<List<MovieEntity>> = tvMovieRepository.loadMovies()
}