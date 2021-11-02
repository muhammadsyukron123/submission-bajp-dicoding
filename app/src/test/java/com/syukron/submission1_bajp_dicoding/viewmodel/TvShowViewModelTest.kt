package com.syukron.submission1_bajp_dicoding.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setup() {
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShows = tvShowViewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}