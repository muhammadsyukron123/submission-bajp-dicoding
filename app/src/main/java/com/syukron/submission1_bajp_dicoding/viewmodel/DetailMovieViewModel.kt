package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.model.EntityData
import com.syukron.submission1_bajp_dicoding.utils.DummyMovies

class DetailMovieViewModel : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): EntityData {
        lateinit var dataEntity: EntityData
        val movies = getMovieModules()
        for (item in movies) {
            if (item.dataId == movieId) {
                dataEntity = item
                break
            }
        }
        return dataEntity
    }

    fun getTvShow(): EntityData {
        lateinit var dataEntity: EntityData
        val tvShows = getTvShowModules()
        for (item in tvShows) {
            if (item.dataId == tvShowId) {
                dataEntity = item
                break
            }
        }
        return dataEntity
    }

    private fun getMovieModules() = DummyMovies.generateDataDummyMovies()

    private fun getTvShowModules() = DummyMovies.generateDataDummyTvShows()

}