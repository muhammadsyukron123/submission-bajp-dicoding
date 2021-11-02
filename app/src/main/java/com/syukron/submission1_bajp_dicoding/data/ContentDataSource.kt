package com.syukron.submission1_bajp_dicoding.data

import androidx.lifecycle.LiveData
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow

interface ContentDataSource {
    fun getMovie(): LiveData<List<Movie>>
    fun getMovieDetail(movieId: String): LiveData<Movie>
    fun getTvShow(): LiveData<List<TvShow>>
    fun getTvShowDetail(tvShowId: String): LiveData<TvShow>
}