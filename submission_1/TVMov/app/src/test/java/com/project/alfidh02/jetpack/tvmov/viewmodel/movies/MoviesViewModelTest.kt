package com.project.alfidh02.jetpack.tvmov.viewmodel.movies


import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {
    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setUp() {
        moviesViewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movieList = moviesViewModel.getMovie()
        assertNotNull(movieList)
        assertEquals(10,movieList.size)
    }
}