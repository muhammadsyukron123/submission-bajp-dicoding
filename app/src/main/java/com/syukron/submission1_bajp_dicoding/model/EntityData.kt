package com.syukron.submission1_bajp_dicoding.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EntityData (
    val dataId: String,
    val imgPoster: Int,
    val imgBg: Int,
    var title: String,
    var releaseDate: String,
    var genre: String,
    var overview: String
): Parcelable