package com.submissionalfi3.tvmov.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import com.submissionalfi3.tvmov.utilities.vo.Resource

class TVViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {
    fun getListTV(): LiveData<Resource<PagedList<TVEntity>>> = tvMovieRepository.getTV()
}