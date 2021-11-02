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
import com.syukron.submission1_bajp_dicoding.adapter.AdapterTvShow
import com.syukron.submission1_bajp_dicoding.model.tvshow.TvShow
import com.syukron.submission1_bajp_dicoding.viewmodel.TvShowViewModel
import com.syukron.submission1_bajp_dicoding.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*


class TvShowFragment : Fragment() {
    companion object {
        const val TVSHOW_TYPE = "tvshow_type"
    }

    private lateinit var tvShowAdapter: AdapterTvShow
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar(true)

        tvShowViewModel = activity?.let { ViewModelProvider(it, ViewModelFactory.getInstance()).get(TvShowViewModel::class.java) }!!
        tvShowViewModel.getTvShow().observe(viewLifecycleOwner, {
            progressBar(false)
            recyclerViewConfig(it)
        })

        savedInstanceState?.putBundle("tv_show_state", savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getBundle("tv_show_state")
    }

    private fun recyclerViewConfig(listTvShow: List<TvShow>) {
        rv_tv_shows_fragment.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 1)
            tvShowAdapter = AdapterTvShow(listTvShow)
            adapter = tvShowAdapter
        }

        tvShowAdapter.setOnViewClickListener(object : AdapterTvShow.TvShowViewClickListener{
            override fun onClick(tvShow: TvShow) {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                    .putExtra(DetailMovieActivity.EXTRA_TITLE, tvShow.original_name)
                    .putExtra(DetailMovieActivity.EXTRA_ID, tvShow.id.toString())
                    .putExtra(DetailMovieActivity.EXTRA_TYPE, TVSHOW_TYPE)

                startActivity(intent)
            }

        })
    }

    private fun progressBar(state: Boolean) {
        if (state) {
            pb_tv_shows.visibility = View.VISIBLE
        } else {
            pb_tv_shows.visibility = View.GONE
        }
    }
}