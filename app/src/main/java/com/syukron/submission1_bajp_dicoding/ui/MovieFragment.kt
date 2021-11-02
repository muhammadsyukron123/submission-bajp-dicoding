package com.syukron.submission1_bajp_dicoding.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.adapter.AdapterMovie
import com.syukron.submission1_bajp_dicoding.model.movie.Movie
import com.syukron.submission1_bajp_dicoding.viewmodel.MovieViewModel
import com.syukron.submission1_bajp_dicoding.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {
    companion object {
        const val MOVIE_TYPE = "movie_type"
    }

    private lateinit var movieAdapter: AdapterMovie
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar(true)

        movieViewModel = activity?.let { ViewModelProvider(it, ViewModelFactory.getInstance())[MovieViewModel::class.java] }!!
        movieViewModel.getMovie().observe(viewLifecycleOwner, {
            progressBar(false)
            recyclerViewConfig(it)
        })
    }

    private fun recyclerViewConfig(listMovie: List<Movie>) {
        rv_movie_fragment.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 1)
            movieAdapter = AdapterMovie(listMovie)
            adapter = movieAdapter
        }

        movieAdapter.setOnViewClickListener(object : AdapterMovie.MovieViewClickListener{
            override fun onClick(movie: Movie) {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                    .putExtra(DetailMovieActivity.EXTRA_TITLE, movie.original_title)
                    .putExtra(DetailMovieActivity.EXTRA_ID, movie.id.toString())
                    .putExtra(DetailMovieActivity.EXTRA_TYPE, MOVIE_TYPE)

                startActivity(intent)
            }

        })
    }

    private fun progressBar(state: Boolean) {
        if (state) {
            pb_movie.visibility = View.VISIBLE
        } else {
            pb_movie.visibility = View.GONE
        }
    }

}