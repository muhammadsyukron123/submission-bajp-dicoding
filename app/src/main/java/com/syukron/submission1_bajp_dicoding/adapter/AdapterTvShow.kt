package com.syukron.submission1_bajp_dicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow
import kotlinx.android.synthetic.main.item_row_movies.view.*

class AdapterTvShow (private val tvShows: List<TvShow>) : RecyclerView.Adapter<AdapterTvShow.TvShowViewHolder>() {

    companion object {
        private const val POSTER_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var tvShowViewClickListener: TvShowViewClickListener

    fun setOnViewClickListener(tvShowViewClickListener: TvShowViewClickListener){
        this.tvShowViewClickListener = tvShowViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movies, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    inner class TvShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow){
            with(itemView){
                Glide.with(this)
                    .load(POSTER_URL + tvShow.poster_path).error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_refresh)
                    .into(img_item_photo)

                tv_title.text = tvShow.original_name
                tv_genre.text = tvShow.vote_average.toString()
            }
            itemView.setOnClickListener{
                tvShowViewClickListener.onClick(tvShow)
            }
        }
    }

    interface TvShowViewClickListener{
        fun onClick(tvShow: TvShow)
    }

}