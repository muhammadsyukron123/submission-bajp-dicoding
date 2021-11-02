package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.data.ContentRepository
import com.syukron.submission1_bajp_dicoding.model.movie.Movie

class MovieViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getMovie(): LiveData<List<Movie>> = contentRepository.getMovie()
}