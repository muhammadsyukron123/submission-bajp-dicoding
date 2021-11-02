package com.syukron.submission1_bajp_dicoding.viewmodel

import com.syukron.submission1_bajp_dicoding.utils.DummyMovies
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val dummyMovies = DummyMovies.generateDataDummyMovies()[0]
    private val dummyTvShows = DummyMovies.generateDataDummyTvShows()[0]
    private val movieId = dummyMovies.dataId
    private val tvShowId = dummyTvShows.dataId

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel()
        detailMovieViewModel.setSelectedMovie(movieId)
        detailMovieViewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getDetailMovies() {
        val movies = detailMovieViewModel.getMovie()
        assertNotNull(movies)
        assertEquals(movies.imgPoster, dummyMovies.imgPoster)
        assertEquals(movies.imgBg, dummyMovies.imgBg)
        assertEquals(movies.title, dummyMovies.title)
        assertEquals(movies.releaseDate, dummyMovies.releaseDate)
        assertEquals(movies.genre, dummyMovies.genre)
        assertEquals(movies.overview, dummyMovies.overview)
    }

    @Test
    fun getDetailTvShows() {
        val tvShows = detailMovieViewModel.getTvShow()
        assertNotNull(tvShows)
        assertEquals(tvShows.imgPoster, dummyTvShows.imgPoster)
        assertEquals(tvShows.imgBg, dummyTvShows.imgBg)
        assertEquals(tvShows.title, dummyTvShows.title)
        assertEquals(tvShows.releaseDate, dummyTvShows.releaseDate)
        assertEquals(tvShows.genre, dummyTvShows.genre)
        assertEquals(tvShows.overview, dummyTvShows.overview)
    }
}