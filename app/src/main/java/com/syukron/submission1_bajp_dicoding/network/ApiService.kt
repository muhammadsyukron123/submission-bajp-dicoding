package com.syukron.submission1_bajp_dicoding.network

import com.syukron.submission1_bajp_dicoding.model.movie.ListMovie
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import com.syukron.submission1_bajp_dicoding.model.tvshow.ListTvShow
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(@Query("api_key") apiKey: String): Call<ListMovie>

    @GET("movie/{movieId}")
    fun getMoviesDetails(@Path("movieId") movieId: String,
                         @Query("api_key") apiKey: String) : Call<Movie>

    @GET("discover/tv")
    fun getTvShows(@Query("api_key") apiKey: String): Call<ListTvShow>

    @GET("tv/{movieId}")
    fun getTvShowsDetails(@Path("movieId") movieId: String,
                          @Query("api_key") apiKey: String) : Call<TvShow>
}