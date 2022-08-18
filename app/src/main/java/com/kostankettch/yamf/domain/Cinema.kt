package com.kostankettch.yamf.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cinema(
    val title: String,
    val poster: Int,
    val description: String,
    val rating: Float = 0f,
    var isFavorite: Boolean = false
) : Parcelable
