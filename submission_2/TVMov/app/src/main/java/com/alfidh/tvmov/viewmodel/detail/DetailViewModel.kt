package com.alfidh.tvmov.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alfidh.tvmov.model.data.entity.DetailEntity
import com.alfidh.tvmov.model.repository.TVMovieRepository

class DetailViewModel(private val tvMovieRepository: TVMovieRepository) : ViewModel() {

    private lateinit var dataDetail: LiveData<DetailEntity>

    companion object {
        const val MOVIE = "detail_movie"
        const val TV = "detail_tv"
    }

    fun setDetailList(id: String, choose: String) {
        when(choose) {
            MOVIE -> {
                dataDetail = tvMovieRepository.loadDetailMovies(id)
            }
            TV -> {
                dataDetail = tvMovieRepository.loadDetailTVShow(id)
            }
        }
    }

    fun getDetailList() = dataDetail
}