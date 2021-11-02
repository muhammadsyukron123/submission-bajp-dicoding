package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.data.ContentRepository
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow

class DetailMovieViewModel(private val contentRepository: ContentRepository): ViewModel() {

    fun getMovieDetail(movieId: String): LiveData<Movie> = contentRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: String): LiveData<TvShow> = contentRepository.getTvShowDetail(tvShowId)
}