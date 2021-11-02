package com.syukron.submission1_bajp_dicoding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syukron.submission1_bajp_dicoding.data.ContentRepository
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow

class TvShowViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getTvShow(): LiveData<List<TvShow>> = contentRepository.getTvShow()
}