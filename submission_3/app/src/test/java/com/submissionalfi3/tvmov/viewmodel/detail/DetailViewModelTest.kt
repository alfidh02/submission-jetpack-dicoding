package com.submissionalfi3.tvmov.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.submissionalfi3.tvmov.model.data.local.entities.DetailEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import com.submissionalfi3.tvmov.utilities.DataDummy
import com.submissionalfi3.tvmov.utilities.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyDataDetailMovie = DataDummy.generateDetailMovie()
    private val dummyDataDetailTV = DataDummy.generateDetailTVShows()

    private val movieID = dummyDataDetailMovie.id
    private val tvShowID = dummyDataDetailTV.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var detailObserver: Observer<Resource<DetailEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(tvMovieRepository)
    }

    @Test
    fun `getDetailMovie should return value and equal as result`() {
        val movie = MutableLiveData<Resource<DetailEntity>>()
        movie.value = Resource.success(DataDummy.generateDetailMovie())

        `when`(tvMovieRepository.getDetailMovie(movieID)).thenReturn(movie)

        viewModel.setDetailMovie(movieID).observeForever(detailObserver)
        verify(detailObserver).onChanged(movie.value)
    }

    @Test
    fun `getDetailTVShow should return value and equal as result`() {
        val tvShow = MutableLiveData<Resource<DetailEntity>>()
        tvShow.value = Resource.success(DataDummy.generateDetailTVShows())

        `when`(tvMovieRepository.getDetailTV(tvShowID)).thenReturn(tvShow)

        viewModel.setDetailTV(tvShowID).observeForever(detailObserver)
        verify(detailObserver).onChanged(tvShow.value)
    }
}