package com.kostankettch.yamf

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cinema(
    val title: String,
    val poster: Int,
    val description: String,
    var isFavorite: Boolean = false
) : Parcelable
