package com.alfidh02.tvmov.viewmodel.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.local.entity.MovieEntity
import com.alfidh02.tvmov.model.data.local.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
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
    fun `getFavoriteListMovie should return value and equals to expected value`() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(2)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(tvMovieRepository.getMoviesFav()).thenReturn(movies)
        val favMovie = viewModel.getFavoriteMovie().value
        verify(tvMovieRepository).getMoviesFav()

        assertNotNull(favMovie)
        assertEquals(2, favMovie?.size)

        viewModel.getFavoriteMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun `getFavoriteListTVShow should return value and equals to expected value`() {
        val dummyTVShow = tvShowPagedList
        `when`(dummyTVShow.size).thenReturn(2)
        val tvShows = MutableLiveData<PagedList<TVEntity>>()
        tvShows.value = dummyTVShow

        `when`(tvMovieRepository.getTVFav()).thenReturn(tvShows)
        val favTVShow = viewModel.getFavoriteTVShow().value
        verify(tvMovieRepository).getTVFav()

        assertNotNull(favTVShow)
        assertEquals(2, favTVShow?.size)

        viewModel.getFavoriteTVShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTVShow)
    }

}