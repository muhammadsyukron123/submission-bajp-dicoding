package com.syukron.submission1_bajp_dicoding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.databinding.ActivityDetailMovieBinding
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow
import com.syukron.submission1_bajp_dicoding.viewmodel.DetailMovieViewModel
import com.syukron.submission1_bajp_dicoding.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailViewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val title = intent.getStringExtra(EXTRA_TITLE)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar(true)

        detailViewModel = ViewModelProvider(this, ViewModelFactory.getInstance()).get(DetailMovieViewModel::class.java)

        val dataId = intent.getStringExtra(EXTRA_ID)
        val dataType = intent.getStringExtra(EXTRA_TYPE)
        if (dataType == MovieFragment.MOVIE_TYPE){
            detailViewModel.getMovieDetail(dataId.toString()).observe(this, {
                progressBar(false)
                setMovieDetail(it)
            })

        } else if(dataType == TvShowFragment.TVSHOW_TYPE){
            detailViewModel.getTvShowDetail(dataId.toString()).observe(this, {
                progressBar(false)
                setTvShowDetail(it)
            })
        }
    }

    private fun setMovieDetail(movie: Movie?) {
        val backdropPath = "https://image.tmdb.org/t/p/w500" + movie?.backdrop_path.toString()
        val posterPath = "https://image.tmdb.org/t/p/w500" + movie?.poster_path.toString()

        Glide.with(this)
            .load(posterPath).error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_refresh)
            .into(iv_detail_poster)
        Glide.with(this)
            .load(backdropPath).error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_refresh)
            .into(iv_detail_bg)
        tv_detail_title.text = movie?.original_title
        tv_detail_genre.text = movie?.genres?.get(0)?.name
        tv_detail_year.text = movie?.release_date
        tv_detail_overview.text = movie?.overview
    }

    private fun setTvShowDetail(tvShow: TvShow?) {
        val backdropPath = "https://image.tmdb.org/t/p/w500" + tvShow?.backdrop_path.toString()
        val posterPath = "https://image.tmdb.org/t/p/w500" + tvShow?.poster_path.toString()

        Glide.with(this)
            .load(posterPath).error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_refresh)
            .into(iv_detail_poster)
        Glide.with(this)
            .load(backdropPath).error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_refresh)
            .into(iv_detail_bg)
        tv_detail_title.text = tvShow?.original_name
        tv_detail_genre.text = tvShow?.genres?.get(0)?.name
        tv_detail_year.text = tvShow?.first_air_date
        tv_detail_overview.text = tvShow?.overview
    }

    private fun progressBar(state: Boolean) {
        if (state) {
            pb_detail.visibility = View.VISIBLE
        } else {
            pb_detail.visibility = View.GONE
        }
    }
}