package com.syukron.submission1_bajp_dicoding.model.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val backdrop_path: String,
    val genres: List<MovieGenre>,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
) : Parcelable