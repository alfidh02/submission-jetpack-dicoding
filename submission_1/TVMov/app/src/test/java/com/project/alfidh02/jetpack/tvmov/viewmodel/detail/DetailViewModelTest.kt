package com.project.alfidh02.jetpack.tvmov.viewmodel.detail

import com.project.alfidh02.jetpack.tvmov.model.utils.MovieTVDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private val dummyMovie = MovieTVDummy.generateMovies()[0]
    private val dummyTv = MovieTVDummy.generateTVShows()[0]
    private val movieTitle = dummyMovie.title
    private val tvTitle = dummyTv.title

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
        detailViewModel.setSelectedMovieTv(movieTitle)
        detailViewModel.setSelectedMovieTv(tvTitle)
    }

    @Test
    fun getMovie() {
        detailViewModel.setSelectedMovieTv(dummyMovie.title)
        val movieModel = detailViewModel.getSelectedMovieTv()
        assertNotNull(movieModel)
        assertEquals(dummyMovie.title, movieModel.title)
        assertEquals(dummyMovie.date, movieModel.date)
        assertEquals(dummyMovie.desc, movieModel.desc)
        assertEquals(dummyMovie.genre, movieModel.genre)
        assertEquals(dummyMovie.pic, movieModel.pic)
    }

    @Test
    fun getTv() {
        detailViewModel.setSelectedMovieTv(dummyTv.title)
        val tvModel = detailViewModel.getSelectedMovieTv()
        assertNotNull(tvModel)
        assertEquals(dummyTv.title, tvModel.title)
        assertEquals(dummyTv.date, tvModel.date)
        assertEquals(dummyTv.desc, tvModel.desc)
        assertEquals(dummyTv.genre, tvModel.genre)
        assertEquals(dummyTv.pic, tvModel.pic)
    }
}