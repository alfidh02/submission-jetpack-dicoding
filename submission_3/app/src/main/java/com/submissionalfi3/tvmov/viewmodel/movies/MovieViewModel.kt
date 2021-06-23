package com.submissionalfi3.tvmov.viewmodel.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import com.submissionalfi3.tvmov.utilities.vo.Resource

class MovieViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {
    fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>> = tvMovieRepository.getMovies()
}