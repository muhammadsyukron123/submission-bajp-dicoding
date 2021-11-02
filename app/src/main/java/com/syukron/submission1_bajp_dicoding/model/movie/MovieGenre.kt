package com.syukron.submission1_bajp_dicoding.model.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieGenre(
    val name: String
) : Parcelable