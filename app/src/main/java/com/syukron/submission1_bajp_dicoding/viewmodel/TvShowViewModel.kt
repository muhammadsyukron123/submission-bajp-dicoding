package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.utils.DummyMovies

class TvShowViewModel : ViewModel() {

    fun getTvShows() = DummyMovies.generateDataDummyTvShows()

}