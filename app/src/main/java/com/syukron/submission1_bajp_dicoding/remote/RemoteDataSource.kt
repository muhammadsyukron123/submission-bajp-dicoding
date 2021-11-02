package com.syukron.submission1_bajp_dicoding.remote

import android.os.Handler
import android.util.Log
import com.syukron.submission1_bajp_dicoding.BuildConfig
import com.syukron.submission1_bajp_dicoding.model.movie.ListMovie
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import com.syukron.submission1_bajp_dicoding.model.tvshow.ListTvShow
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow
import com.syukron.submission1_bajp_dicoding.network.RetrofitClient
import com.syukron.submission1_bajp_dicoding.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val retroClient: RetrofitClient) {

    private val handler = Handler()

    companion object {
        private const val apiKey = BuildConfig.API_KEY
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(retroClient: RetrofitClient): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(retroClient)
            }
    }

    fun getMovies(movieCallback: MovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retroClient.createService().getMovies(apiKey)
                .enqueue(object : Callback<ListMovie> {
                    override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {
                        if (response.isSuccessful){
                            val responseBody = response.body()
                            if (responseBody != null){
                                val result = responseBody.results
                                movieCallback.onResponse(result)
                            }
                        }
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                        Log.d("OnFailure", t.message.toString())
                    }


                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMoviesDetails(movieId: String, movieDetailCallback: MovieDetailCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retroClient.createService().getMoviesDetails(movieId, apiKey)
                .enqueue(object : Callback<Movie> {
                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        if (response.isSuccessful){
                            val responseBody = response.body()
                            if (responseBody != null){
                                movieDetailCallback.onResponse(responseBody)
                            }
                        }
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        Log.d("OnFailure", t.message.toString())
                    }

                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShows(tvShowCallback: TvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retroClient.createService().getTvShows(apiKey)
                .enqueue(object : Callback<ListTvShow> {
                    override fun onResponse(call: Call<ListTvShow>, response: Response<ListTvShow>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                val result = responseBody.results
                                tvShowCallback.onResponse(result)
                            }
                        }
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<ListTvShow>, t: Throwable) {
                        Log.d("OnFailure", t.message.toString())
                    }

                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShowsDetails(movieId: String, tvShowDetailCallback: TvShowDetailCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            retroClient.createService().getTvShowsDetails(movieId, apiKey)
                .enqueue(object : Callback<TvShow> {
                    override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                        if (response.isSuccessful){
                            val responseBody = response.body()
                            if (responseBody != null){
                                tvShowDetailCallback.onResponse(responseBody)
                            }
                        }
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<TvShow>, t: Throwable) {
                        Log.d("OnFailure", t.message.toString())
                    }

                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface MovieCallback {
        fun onResponse(movieResponse: List<Movie>)
    }

    interface MovieDetailCallback {
        fun onResponse(movieDetailResponse: Movie)
    }

    interface TvShowCallback {
        fun onResponse(tvShowResponse: List<TvShow>)
    }

    interface TvShowDetailCallback {
        fun onResponse(tvShowDetail: TvShow)
    }

}