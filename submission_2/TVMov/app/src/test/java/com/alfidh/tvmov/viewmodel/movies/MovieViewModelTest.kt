package com.alfidh.tvmov.viewmodel.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.alfidh.tvmov.model.repository.TVMovieRepository
import com.alfidh.tvmov.testingutil.DataDummy
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(tvMovieRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DataDummy.generateMovie()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(tvMovieRepository.loadMovies()).thenReturn(movies)
        val tvEntities = viewModel.getListMovie().value
        verify(tvMovieRepository).loadMovies()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getListMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}