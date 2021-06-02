package com.alfidh.tvmov.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alfidh.tvmov.model.data.entity.DetailEntity
import com.alfidh.tvmov.model.repository.TVMovieRepository
import com.alfidh.tvmov.testingutil.DetailDataDummy
import com.alfidh.tvmov.viewmodel.detail.DetailViewModel.Companion.MOVIE
import com.alfidh.tvmov.viewmodel.detail.DetailViewModel.Companion.TV
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyDetailMovie = DetailDataDummy.generateDetailMovie()
    private val movieID = dummyDetailMovie.id.toString()

    private val dummyDetailTVShow = DetailDataDummy.generateDetailTVShows()
    private val tvShowID = dummyDetailTVShow.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var movieObserver: Observer<DetailEntity>

    @Mock
    private lateinit var tvObserver: Observer<DetailEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(tvMovieRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<DetailEntity>()
        movie.value = dummyDetailMovie

        Mockito.`when`(tvMovieRepository.loadDetailMovies(movieID)).thenReturn(movie)
        viewModel.setDetailList(movieID, MOVIE)
        val detailMovie = viewModel.getDetailList().value
        verify(tvMovieRepository).loadDetailMovies(movieID)
        assertNotNull(detailMovie)

       with(dummyDetailMovie) {
            assertEquals(id, detailMovie?.id)
            assertEquals(title, detailMovie?.title)
            assertEquals(date, detailMovie?.date)
            assertEquals(pic, detailMovie?.pic)
            assertEquals(rate, detailMovie?.rate)
            assertEquals(genres, detailMovie?.genres)
            assertEquals(desc, detailMovie?.desc)
        }

        viewModel.getDetailList().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun getTVShow() {
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyDetailTVShow

        Mockito.`when`(tvMovieRepository.loadDetailTVShow(tvShowID)).thenReturn(tvShow)
        viewModel.setDetailList(tvShowID, TV)
        val detailTVShow = viewModel.getDetailList().value
        verify(tvMovieRepository).loadDetailTVShow(tvShowID)
        assertNotNull(detailTVShow)

       with(dummyDetailTVShow) {
            assertEquals(id, detailTVShow?.id)
            assertEquals(title, detailTVShow?.title)
            assertEquals(date, detailTVShow?.date)
            assertEquals(pic, detailTVShow?.pic)
            assertEquals(rate, detailTVShow?.rate)
            assertEquals(genres, detailTVShow?.genres)
            assertEquals(desc, detailTVShow?.desc)
        }

        viewModel.getDetailList().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyDetailTVShow)
    }
}