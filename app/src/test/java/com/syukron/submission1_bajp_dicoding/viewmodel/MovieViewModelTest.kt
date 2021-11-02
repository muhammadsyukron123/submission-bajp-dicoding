package com.syukron.submission1_bajp_dicoding.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setup() {
        movieViewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = movieViewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}