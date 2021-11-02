package com.syukron.submission1_bajp_dicoding.di

import com.syukron.submission1_bajp_dicoding.data.ContentRepository
import com.syukron.submission1_bajp_dicoding.network.RetrofitClient
import com.syukron.submission1_bajp_dicoding.remote.RemoteDataSource

object Injection {

    fun movieRepository(): ContentRepository {
        val remoteRepository = RemoteDataSource.getInstance(RetrofitClient)
        return ContentRepository.getInstance(remoteRepository)
    }

}