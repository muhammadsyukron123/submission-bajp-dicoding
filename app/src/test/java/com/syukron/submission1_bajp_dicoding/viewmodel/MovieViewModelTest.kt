package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.syukron.submission1_bajp_dicoding.data.ContentRepository
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
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
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setup() {
        movieViewModel = MovieViewModel(contentRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DummyData.generateDummyMovies()
        val movie = MutableLiveData<List<Movie>>()
        movie.value = dummyMovies

        Mockito.`when`(contentRepository.getMovie()).thenReturn(movie)
        val movieEntity = movieViewModel.getMovie().value
        verify(contentRepository).getMovie()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        movieViewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }
}