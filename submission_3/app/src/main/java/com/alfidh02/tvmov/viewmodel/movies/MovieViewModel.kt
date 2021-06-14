package com.alfidh02.tvmov.viewmodel.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.vo.Resource

class MovieViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {
    fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>> = tvMovieRepository.loadMovies()
}