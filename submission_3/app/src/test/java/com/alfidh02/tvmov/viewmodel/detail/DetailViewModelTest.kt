package com.alfidh02.tvmov.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.util.DetailDataDummy
import com.alfidh02.tvmov.vo.Resource
import com.nhaarman.mockitokotlin2.verify
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
    private val dummyDetailTVShow = DetailDataDummy.generateDetailTVShows()

    private val movieID = dummyDetailMovie.id
    private val tvShowID = dummyDetailTVShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TVEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(tvMovieRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = Resource.success(DetailDataDummy.generateDetailMovie())

        Mockito.`when`(tvMovieRepository.loadDetailMovies(movieID)).thenReturn(movie)
        viewModel.setDataMovie(movieID).observeForever(movieObserver)
        verify(movieObserver).onChanged(movie.value)
    }

    @Test
    fun getDetailTVShow() {
        val tvShow = MutableLiveData<Resource<TVEntity>>()
        tvShow.value = Resource.success(DetailDataDummy.generateDetailTVShows())

        Mockito.`when`(tvMovieRepository.loadDetailTVShow(tvShowID)).thenReturn(tvShow)
        viewModel.setDataTV(tvShowID).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShow.value)
    }

    @Test
    fun setMovieFav() {
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = Resource.success(DetailDataDummy.generateDetailMovie())

        Mockito.`when`(tvMovieRepository.loadDetailMovies(movieID)).thenReturn(movies)

        viewModel.setDataMovie(movieID).observeForever(movieObserver)
        viewModel.setMovieFavorite()
        verify(tvMovieRepository).setMoviesFav((movies.value?.data) as MovieEntity, true)
    }

    @Test
    fun setTVShowFav() {
        val tvShow = MutableLiveData<Resource<TVEntity>>()
        tvShow.value = Resource.success(DetailDataDummy.generateDetailTVShows())

        Mockito.`when`(tvMovieRepository.loadDetailTVShow(tvShowID)).thenReturn(tvShow)

        viewModel.setDataTV(tvShowID).observeForever(tvShowObserver)
        viewModel.setTVFavorite()
        verify(tvMovieRepository).setTVShowsFav((tvShow.value?.data) as TVEntity, true)
    }
}