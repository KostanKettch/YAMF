package com.kostankettch.yamf.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cinema(
    val title: String,
    val poster: String,
    val description: String,
    val rating: Double = 0.0,
    var isFavorite: Boolean = false
) : Parcelable
