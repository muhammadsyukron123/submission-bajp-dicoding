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
import com.syukron.submission1_bajp_dicoding.databinding.FragmentTvShowBinding
import com.syukron.submission1_bajp_dicoding.model.EntityData
import com.syukron.submission1_bajp_dicoding.viewmodel.TvShowViewModel


class TvShowFragment : Fragment() {
    companion object {
        const val TVSHOW_TYPE = "tvshow_type"
    }

    private var movieAdapter = AdapterMovie()
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel::class.java)

        movieAdapter.setData(tvShowViewModel.getTvShows())

        fragmentTvShowBinding.rvTvShowsFragment.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1)
            adapter = movieAdapter
        }

        movieAdapter.setOnViewClickListener(object : AdapterMovie.ViewClickListener{
            override fun onClick(dataEntity: EntityData) {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                    .putExtra(DetailMovieActivity.EXTRA_TITLE, dataEntity.title)
                    .putExtra(DetailMovieActivity.EXTRA_ID, dataEntity.dataId)
                    .putExtra(DetailMovieActivity.EXTRA_TYPE, TVSHOW_TYPE)
                startActivity(intent)
            }

        })
    }
}