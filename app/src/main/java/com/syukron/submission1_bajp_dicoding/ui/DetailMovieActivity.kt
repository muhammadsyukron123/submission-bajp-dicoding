package com.syukron.submission1_bajp_dicoding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.syukron.submission1_bajp_dicoding.databinding.ActivityDetailMovieBinding
import com.syukron.submission1_bajp_dicoding.model.EntityData
import com.syukron.submission1_bajp_dicoding.viewmodel.DetailMovieViewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var activityDetailBinding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailMovieViewModel::class.java)

        val dataId = intent.getStringExtra(EXTRA_ID)
        val dataType = intent.getStringExtra(EXTRA_TYPE)
        if (dataType == MovieFragment.MOVIE_TYPE){
            detailViewModel.setSelectedMovie(dataId.toString())
            setDetail(detailViewModel.getMovie())

        } else if(dataType == TvShowFragment.TVSHOW_TYPE){
            detailViewModel.setSelectedTvShow(dataId.toString())
            setDetail(detailViewModel.getTvShow())
        }
    }

    private fun setDetail(detail: EntityData?){
        Glide.with(this).load(detail?.imgPoster).into(activityDetailBinding.ivDetailPoster)
        Glide.with(this).load(detail?.imgBg).into(activityDetailBinding.ivDetailBg)
        activityDetailBinding.tvDetailTitle.text = detail?.title
        activityDetailBinding.tvDetailYear.text = detail?.releaseDate
        activityDetailBinding.tvDetailGenre.text = detail?.genre
        activityDetailBinding.tvDetailOverview.text = detail?.overview
    }

}