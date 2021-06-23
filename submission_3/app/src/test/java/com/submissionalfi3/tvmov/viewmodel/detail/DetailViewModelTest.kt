package com.submissionalfi3.tvmov.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.model.repository.TVMovieRepository
import com.submissionalfi3.tvmov.utilities.DataDummy
import com.submissionalfi3.tvmov.utilities.vo.Resource
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
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TVEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(tvMovieRepository)
    }

    @Test
    fun `getDetailMovie should return value`() {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = Resource.success(DataDummy.generateDetailMovie())

        `when`(tvMovieRepository.getDetailMovie(movieID)).thenReturn(movie)
        viewModel.setDataMovie(movieID).observeForever(movieObserver)
        verify(movieObserver).onChanged(movie.value)
    }

    @Test
    fun `getDetailTVShow should return value`() {
        val tvShow = MutableLiveData<Resource<TVEntity>>()
        tvShow.value = Resource.success(DataDummy.generateDetailTVShows())

        `when`(tvMovieRepository.getDetailTV(tvShowID)).thenReturn(tvShow)
        viewModel.setDataTV(tvShowID).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShow.value)
    }

    @Test
    fun `setFavoriteMovies should success`() {
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = Resource.success(DataDummy.generateDetailMovie())

        `when`(tvMovieRepository.getDetailMovie(movieID)).thenReturn(movies)

        viewModel.setDataMovie(movieID).observeForever(movieObserver)
        viewModel.setMovieFavorite()
        verify(tvMovieRepository).setMoviesFav((movies.value?.data) as MovieEntity, true)
    }

    @Test
    fun `setFavoriteTVShows should success`() {
        val tvShow = MutableLiveData<Resource<TVEntity>>()
        tvShow.value = Resource.success(DataDummy.generateDetailTVShows())

        `when`(tvMovieRepository.getDetailTV(tvShowID)).thenReturn(tvShow)

        viewModel.setDataTV(tvShowID).observeForever(tvShowObserver)
        viewModel.setTVFavorite()
        verify(tvMovieRepository).setTVFav((tvShow.value?.data) as TVEntity, true)
    }
}