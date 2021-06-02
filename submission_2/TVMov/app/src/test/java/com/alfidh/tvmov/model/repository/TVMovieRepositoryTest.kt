package com.alfidh.tvmov.model.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alfidh.tvmov.model.data.remote.source.RemoteDataSource
import com.alfidh.tvmov.testingutil.DataDummy
import com.alfidh.tvmov.testingutil.DetailDataDummy
import com.alfidh.tvmov.testingutil.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TVMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val tvMovieRepository = FakeTVMovieRepository(remote)

    private val remoteMovie = DataDummy.generateRemoteMovies()
    private val remoteTVShow = DataDummy.generateRemoteTVShows()

    private val movieID = remoteMovie[0].id.toString()
    private val tvShowID = remoteTVShow[0].id.toString()

    private val detailRemoteMovie = DetailDataDummy.generateDetailRemoteMovies()
    private val detailRemoteTVShow = DetailDataDummy.generateDetailRemoteTVShows()

    @Test
    fun getListMovies() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMoviesReceived(remoteMovie)
            null
        }.`when`(remote).getTopMovies(any())

        val movieEntity = LiveDataTestUtil.getValue(tvMovieRepository.loadMovies())
        verify(remote).getTopMovies(any())
        assertNotNull(movieEntity)
        assertEquals(remoteMovie.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadDetailMovieCallback)
                .onAllDetailMoviesReceived(detailRemoteMovie)
            null
        }.`when`(remote).getDetailMovies(any(), eq(movieID))

        val movieDetailEntity =
            LiveDataTestUtil.getValue(tvMovieRepository.loadDetailMovies(movieID))
        verify(remote).getDetailMovies(any(), eq(movieID))
        assertNotNull(movieDetailEntity)
        assertEquals(detailRemoteMovie.id, movieDetailEntity.id)
    }

    @Test
    fun getListTVShows() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTVCallback)
                .onAllTVShowsReceived(remoteTVShow)
            null
        }.`when`(remote).getTopTV(any())

        val tvShowEntity = LiveDataTestUtil.getValue(tvMovieRepository.loadTVShow())
        verify(remote).getTopTV(any())
        assertNotNull(tvShowEntity)
        assertEquals(remoteTVShow.size.toLong(), tvShowEntity.size.toLong())
    }

    @Test
    fun getDetailTVShows() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadDetailTVCallback)
                .onAllDetailTVShowsReceived(detailRemoteTVShow)
            null
        }.`when`(remote).getDetailTV(any(), eq(tvShowID))

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(tvMovieRepository.loadDetailTVShow(tvShowID))
        verify(remote).getDetailTV(any(), eq(tvShowID))
        assertNotNull(tvShowDetailEntity)
        assertEquals(detailRemoteTVShow.id, tvShowDetailEntity.id)
    }
}