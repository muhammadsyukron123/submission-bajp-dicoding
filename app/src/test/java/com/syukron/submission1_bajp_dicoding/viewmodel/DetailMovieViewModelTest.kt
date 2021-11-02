package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.syukron.submission1_bajp_dicoding.data.ContentRepository
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow
import com.syukron.submission1_bajp_dicoding.utils.DummyData
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var movieObserver: Observer<Movie>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShow>


    @Before
    fun setup() {
        detailViewModel = DetailMovieViewModel(contentRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovies = DummyData.generateDummyDetailMovie()
        val movieDetail = MutableLiveData<Movie>()
        movieDetail.value = dummyDetailMovies
        val id = movieDetail.value!!.id.toString()

        Mockito.`when`(contentRepository.getMovieDetail(id)).thenReturn(movieDetail)
        val movieDetailEntity = detailViewModel.getMovieDetail(id).value
        verify(contentRepository).getMovieDetail(id)

        assertNotNull(movieDetailEntity)
        assertEquals(movieDetailEntity?.id, contentRepository.getMovieDetail(id).value?.id)
        assertEquals(movieDetailEntity?.backdrop_path, contentRepository.getMovieDetail(id).value?.backdrop_path)
        assertEquals(movieDetailEntity?.genres, contentRepository.getMovieDetail(id).value?.genres)
        assertEquals(movieDetailEntity?.original_title, contentRepository.getMovieDetail(id).value?.original_title)
        assertEquals(movieDetailEntity?.overview, contentRepository.getMovieDetail(id).value?.overview)
        assertEquals(movieDetailEntity?.poster_path, contentRepository.getMovieDetail(id).value?.poster_path)
        assertEquals(movieDetailEntity?.release_date, contentRepository.getMovieDetail(id).value?.release_date)

        contentRepository.getMovieDetail(id).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovies)

    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShows = DummyData.generateDummyDetailTvShow()
        val tvShowDetail = MutableLiveData<TvShow>()
        tvShowDetail.value = dummyDetailTvShows
        val id = tvShowDetail.value!!.id.toString()

        Mockito.`when`(contentRepository.getTvShowDetail(id)).thenReturn(tvShowDetail)
        val tvShowDetailEntity = detailViewModel.getTvShowDetail(id).value
        verify(contentRepository).getTvShowDetail(id)

        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetailEntity?.id, contentRepository.getTvShowDetail(id).value?.id)
        assertEquals(tvShowDetailEntity?.backdrop_path, contentRepository.getTvShowDetail(id).value?.backdrop_path)
        assertEquals(tvShowDetailEntity?.first_air_date, contentRepository.getTvShowDetail(id).value?.first_air_date)
        assertEquals(tvShowDetailEntity?.genres, contentRepository.getTvShowDetail(id).value?.genres)
        assertEquals(tvShowDetailEntity?.original_name, contentRepository.getTvShowDetail(id).value?.original_name)
        assertEquals(tvShowDetailEntity?.overview, contentRepository.getTvShowDetail(id).value?.overview)
        assertEquals(tvShowDetailEntity?.poster_path, contentRepository.getTvShowDetail(id).value?.poster_path)

        contentRepository.getTvShowDetail(id).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShows)
    }
}