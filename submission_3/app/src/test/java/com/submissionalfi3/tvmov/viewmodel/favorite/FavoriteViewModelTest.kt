package com.submissionalfi3.tvmov.viewmodel.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>
    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TVEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>
    @Mock
    private lateinit var tvShowPagedList: PagedList<TVEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(tvMovieRepository)
    }

    @Test
    fun `getFavoriteListMovie should return value and size equals to expected value`() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(2)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(tvMovieRepository.getMoviesFav()).thenReturn(movies)
        val favMovie = viewModel.getFavoriteMovie().value
        verify(tvMovieRepository).getMoviesFav()

        assertNotNull(favMovie)
        assertEquals(movies.value, favMovie)
        assertEquals(movies.value?.size, favMovie?.size)

        viewModel.getFavoriteMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun `getFavoriteListTVShow should return value and size equals to expected value`() {
        val dummyTVShow = tvShowPagedList
        `when`(dummyTVShow.size).thenReturn(2)
        val tvShows = MutableLiveData<PagedList<TVEntity>>()
        tvShows.value = dummyTVShow

        `when`(tvMovieRepository.getTVFav()).thenReturn(tvShows)
        val favTVShow = viewModel.getFavoriteTVShow().value
        verify(tvMovieRepository).getTVFav()

        assertNotNull(favTVShow)
        assertEquals(tvShows.value, favTVShow)
        assertEquals(tvShows.value?.size, favTVShow?.size)

        viewModel.getFavoriteTVShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTVShow)
    }

}