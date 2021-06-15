package com.submissionalfi3.tvmov.model.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.submissionalfi3.tvmov.model.data.local.LocalDataSource
import com.submissionalfi3.tvmov.model.data.local.entity.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entity.TVEntity
import com.submissionalfi3.tvmov.model.data.remote.RemoteDataSource
import com.submissionalfi3.tvmov.testutil.*
import com.submissionalfi3.tvmov.testutil.vo.Resource
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
class TVMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteMock = mock(RemoteDataSource::class.java)
    private val localMock = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val tvMovieRepository = FakeTVMovieRepository(remoteMock, localMock, appExecutors)

    private val remoteMovie = DataDummy.generateRemoteMovies()
    private val remoteTVShow = DataDummy.generateRemoteTVShows()

    private val movieID = remoteMovie[0].id
    private val tvShowID = remoteTVShow[0].id

    private val detailRemoteMovie = DataDummy.generateDetailRemoteMovies()
    private val detailRemoteTVShow = DataDummy.generateDetailRemoteTVShows()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localMock.getMovies()).thenReturn(dataSourceFactory)
        tvMovieRepository.getMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovie()))
        verify(localMock).getMovies()
        assertNotNull(movieEntity.data)
        assertEquals(remoteMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTVShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        `when`(localMock.getTV()).thenReturn(dataSourceFactory)
        tvMovieRepository.getTV()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTVShows()))
        verify(localMock).getTV()
        assertNotNull(tvShowEntity)
        assertEquals(remoteTVShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.generateDetailMovie()
        `when`(localMock.getMovieById(movieID)).thenReturn(dummyDetail)

        val movieDetailEntity =
            LiveDataTestUtil.getValue(tvMovieRepository.getDetailMovie(movieID))
        verify(localMock).getMovieById(movieID)
        assertNotNull(movieDetailEntity)
        assertEquals(detailRemoteMovie.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getDetailTVShows() {
        val dummyDetail = MutableLiveData<TVEntity>()
        dummyDetail.value = DataDummy.generateDetailTVShows()
        `when`(localMock.getTVById(tvShowID)).thenReturn(dummyDetail)

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(tvMovieRepository.getDetailTV(tvShowID))
        verify(localMock).getTVById(tvShowID)
        assertNotNull(tvShowDetailEntity)
        assertEquals(detailRemoteTVShow.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun setFavoriteMovie() {
        tvMovieRepository.setMoviesFav(DataDummy.generateDetailMovie(), true)
        verify(localMock).setMovieFav(DataDummy.generateDetailMovie(), true)
        verifyNoMoreInteractions(localMock)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localMock.getMoviesFav()).thenReturn(dataSourceFactory)
        tvMovieRepository.getMoviesFav()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovie()))
        verify(localMock).getMoviesFav()
        assertNotNull(movieEntities)
        assertEquals(remoteMovie.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteTVShows() {
        tvMovieRepository.setTVFav(DataDummy.generateDetailTVShows(), true)
        verify(localMock).setTVFav(DataDummy.generateDetailTVShows(), true)
        verifyNoMoreInteractions(localMock)
    }

    @Test
    fun getFavoriteTVShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        `when`(localMock.getTVFav()).thenReturn(dataSourceFactory)
        tvMovieRepository.getTVFav()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTVShows()))
        verify(localMock).getTVFav()
        assertNotNull(tvEntities)
        assertEquals(remoteTVShow.size, tvEntities.data?.size)
    }
}