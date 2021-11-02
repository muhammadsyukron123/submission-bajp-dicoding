package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.utils.DummyMovies

class MovieViewModel : ViewModel() {

    fun getMovies() = DummyMovies.generateDataDummyMovies()

}