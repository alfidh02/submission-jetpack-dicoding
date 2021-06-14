package com.alfidh02.tvmov.viewmodel.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.util.DetailDataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    fun setFavListMovies() {
        viewModel.setFavListMovie(DetailDataDummy.generateDetailMovie())
        verify(tvMovieRepository).setMoviesFav(DetailDataDummy.generateDetailMovie(), true)
        verifyNoMoreInteractions(tvMovieRepository)
    }

    @Test
    fun setFavListTVShows() {
        viewModel.setFavListTV(DetailDataDummy.generateDetailTVShows())
        verify(tvMovieRepository).setTVShowsFav(DetailDataDummy.generateDetailTVShows(), true)
        verifyNoMoreInteractions(tvMovieRepository)
    }

    @Test
    fun getFavListMovies() {
        val dummyMovie = moviePagedList
        Mockito.`when`(dummyMovie.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(tvMovieRepository.getMoviesFav()).thenReturn(movies)
        val favMovie = viewModel.getFavListMovie().value
        verify(tvMovieRepository).getMoviesFav()
        assertNotNull(favMovie)
        assertEquals(5, favMovie?.size)

        viewModel.getFavListMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getFavListTVShow() {
        val dummyTVShow = tvShowPagedList
        Mockito.`when`(dummyTVShow.size).thenReturn(5)
        val tvShows = MutableLiveData<PagedList<TVEntity>>()
        tvShows.value = dummyTVShow

        Mockito.`when`(tvMovieRepository.getTVShowsFav()).thenReturn(tvShows)
        val favTVShow = viewModel.getFavListTV().value
        verify(tvMovieRepository).getTVShowsFav()
        assertNotNull(favTVShow)
        assertEquals(5, favTVShow?.size)

        viewModel.getFavListTV().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTVShow)
    }

}