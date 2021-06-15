package com.submissionalfi3.tvmov.viewmodel.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entity.MovieEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import com.submissionalfi3.tvmov.testutil.vo.Resource

class MovieViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {
    fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>> = tvMovieRepository.getMovies()
}