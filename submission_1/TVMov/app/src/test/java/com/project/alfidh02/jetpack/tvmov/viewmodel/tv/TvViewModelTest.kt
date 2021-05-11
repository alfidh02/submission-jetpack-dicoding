package com.project.alfidh02.jetpack.tvmov.viewmodel.tv

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvViewModelTest {
    private lateinit var tvViewModel : TvViewModel

    @Before
    fun setUp() {
        tvViewModel = TvViewModel()
    }

    @Test
    fun getTv() {
        val tvList = tvViewModel.getTV()
        assertNotNull(tvList)
        assertEquals(10, tvList.size)
    }
}