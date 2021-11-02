package com.syukron.submission1_bajp_dicoding.model.tvshow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val backdrop_path: String,
    val first_air_date: String,
    val genres: List<TvShowGenre>,
    val original_name: String,
    val overview: String,
    val poster_path: String,
    val vote_average: Double
) : Parcelable