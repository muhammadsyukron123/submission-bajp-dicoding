package com.syukron.submission1_bajp_dicoding.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.syukron.submission1_bajp_dicoding.adapter.AdapterMovie
import com.syukron.submission1_bajp_dicoding.databinding.FragmentMovieBinding
import com.syukron.submission1_bajp_dicoding.model.EntityData
import com.syukron.submission1_bajp_dicoding.viewmodel.MovieViewModel


class MovieFragment : Fragment() {
    companion object {
        const val MOVIE_TYPE = "movie_type"
    }

    private var movieAdapter = AdapterMovie()
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)

        movieAdapter.setData(movieViewModel.getMovies())

        fragmentMovieBinding.rvMovieFragment.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1)
            adapter = movieAdapter
        }

        movieAdapter.setOnViewClickListener(object : AdapterMovie.ViewClickListener{
            override fun onClick(dataEntity: EntityData) {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                    .putExtra(DetailMovieActivity.EXTRA_TITLE, dataEntity.title)
                    .putExtra(DetailMovieActivity.EXTRA_ID, dataEntity.dataId)
                    .putExtra(DetailMovieActivity.EXTRA_TYPE, MOVIE_TYPE)
                startActivity(intent)
            }

        })
    }
}