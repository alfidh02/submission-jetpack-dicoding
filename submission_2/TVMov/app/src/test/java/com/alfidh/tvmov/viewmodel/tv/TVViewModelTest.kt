package com.alfidh.tvmov.viewmodel.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alfidh.tvmov.model.data.entity.TVEntity
import com.alfidh.tvmov.model.repository.TVMovieRepository
import com.alfidh.tvmov.testingutil.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVViewModelTest {
    private lateinit var viewModel: TVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var observer: Observer<List<TVEntity>>

    @Before
    fun setUp() {
        viewModel = TVViewModel(tvMovieRepository)
    }

    @Test
    fun getTVShow() {
        val dummyTVShow = DataDummy.generateTVShows()
        val tvShows = MutableLiveData<List<TVEntity>>()
        tvShows.value = dummyTVShow

        `when`(tvMovieRepository.loadTVShow()).thenReturn(tvShows)
        val tvEntities = viewModel.getListTV().value
        verify(tvMovieRepository).loadTVShow()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getListTV().observeForever(observer)
        verify(observer).onChanged(dummyTVShow)
    }

}