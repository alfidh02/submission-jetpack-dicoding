package com.alfidh02.tvmov.viewmodel.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.vo.Resource

class TVViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {
    fun getListTV(): LiveData<Resource<PagedList<TVEntity>>> = tvMovieRepository.loadTVShow()
}