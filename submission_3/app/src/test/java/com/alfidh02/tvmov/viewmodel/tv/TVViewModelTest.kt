package com.alfidh02.tvmov.viewmodel.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.repository.TVMovieRepository
import com.alfidh02.tvmov.vo.Resource
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
class TVViewModelTest {
    private lateinit var viewModel: TVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvMovieRepository: TVMovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVEntity>>>

    @Mock
    private lateinit var tvPagedList: PagedList<TVEntity>

    @Before
    fun setUp() {
        viewModel = TVViewModel(tvMovieRepository)
    }

    @Test
    fun getTVShow() {
        val dummyTV = Resource.success(tvPagedList)
        `when`(dummyTV.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TVEntity>>>()
        tv.value = dummyTV

        `when`(tvMovieRepository.loadTVShow()).thenReturn(tv)
        val movieEntity = viewModel.getListTV().value?.data
        verify(tvMovieRepository).loadTVShow()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getListTV().observeForever(observer)
        verify(observer).onChanged(dummyTV)
    }
}