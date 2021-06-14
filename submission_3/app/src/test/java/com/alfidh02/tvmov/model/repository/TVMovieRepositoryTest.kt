package com.alfidh02.tvmov.model.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.data.remote.source.LocalDataSource
import com.alfidh02.tvmov.model.data.remote.source.RemoteDataSource
import com.alfidh02.tvmov.util.*
import com.alfidh02.tvmov.vo.Resource
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@Suppress("UNCHECKED_CAST")
class TVMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val tvMovieRepository = FakeTVMovieRepository(remote, local, appExecutors)

    private val remoteMovie = DataDummy.generateRemoteMovies()
    private val remoteTVShow = DataDummy.generateRemoteTVShows()

    private val movieID = remoteMovie[0].id
    private val tvShowID = remoteTVShow[0].id

    private val detailRemoteMovie = DetailDataDummy.generateDetailRemoteMovies()
    private val detailRemoteTVShow = DetailDataDummy.generateDetailRemoteTVShows()

    @Test
    fun getListMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovie()).thenReturn(dataSourceFactory)
        tvMovieRepository.loadMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovie()))
        verify(local).getAllMovie()
        assertNotNull(movieEntity.data)
        assertEquals(remoteMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getListTVShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        Mockito.`when`(local.getAllTV()).thenReturn(dataSourceFactory)
        tvMovieRepository.loadTVShow()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTVShows()))
        verify(local).getAllTV()
        assertNotNull(tvShowEntity)
        assertEquals(remoteTVShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DetailDataDummy.generateDetailMovie()
        Mockito.`when`(local.getMovieById(movieID)).thenReturn(dummyDetail)

        val movieDetailEntity =
            LiveDataTestUtil.getValue(tvMovieRepository.loadDetailMovies(movieID))
        verify(local).getMovieById(movieID)
        assertNotNull(movieDetailEntity)
        assertEquals(detailRemoteMovie.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getDetailTVShows() {
        val dummyDetail = MutableLiveData<TVEntity>()
        dummyDetail.value = DetailDataDummy.generateDetailTVShows()
        Mockito.`when`(local.getTVById(tvShowID)).thenReturn(dummyDetail)

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(tvMovieRepository.loadDetailTVShow(tvShowID))
        verify(local).getTVById(tvShowID)
        assertNotNull(tvShowDetailEntity)
        assertEquals(detailRemoteTVShow.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun setFavMovie() {
        tvMovieRepository.setMoviesFav(DetailDataDummy.generateDetailMovie(), true)
        verify(local).updateFavMovie(DetailDataDummy.generateDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        tvMovieRepository.getMoviesFav()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovie()))
        verify(local).getFavMovies()
        assertNotNull(movieEntities)
        assertEquals(remoteMovie.size, movieEntities.data?.size)
    }
}