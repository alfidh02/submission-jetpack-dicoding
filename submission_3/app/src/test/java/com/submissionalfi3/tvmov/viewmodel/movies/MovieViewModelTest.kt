package com.submissionalfi3.tvmov.viewmodel.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(tvMovieRepository)
    }

    @Test
    fun `getMovie should success and size equals to expected value`() {
        val dummyMovie = Resource.success(moviePagedList)
        `when`(dummyMovie.data?.size).thenReturn(2)

        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(tvMovieRepository.getMovies()).thenReturn(movies)
        val movieEntity = viewModel.getListMovie().value
        verify(tvMovieRepository).getMovies()

        assertNotNull(movieEntity)
        assertEquals(movies.value?.data, movieEntity?.data)
        assertEquals(movies.value?.data?.size, movieEntity?.data?.size)

        viewModel.getListMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}