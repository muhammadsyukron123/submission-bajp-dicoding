package com.syukron.submission1_bajp_dicoding.adapter

import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import kotlinx.android.synthetic.main.item_row_movies.view.*

class AdapterMovie(private val movies : List<Movie>) : RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    companion object {
        private const val POSTER_URL = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var movieViewClickListener: MovieViewClickListener

    fun setOnViewClickListener(movieViewClickListener: MovieViewClickListener){
        this.movieViewClickListener = movieViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie : Movie){
            with(itemView){
                Glide.with(this)
                    .load(POSTER_URL + movie.poster_path).error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_refresh)
                    .into(img_item_photo)

                tv_title.text = movie.original_title
                tv_genre.text = movie.vote_average.toString()
            }
            itemView.setOnClickListener{
                movieViewClickListener.onClick(movie)
            }
        }
    }

    interface MovieViewClickListener{
        fun onClick(movie: Movie)
    }

}