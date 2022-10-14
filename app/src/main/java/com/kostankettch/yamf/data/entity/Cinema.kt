package com.kostankettch.yamf.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cached_films", indices = [Index(value = ["title"], unique = true)])
data class Cinema(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo (name = "title") val title: String,
    @ColumnInfo (name = "poster") val poster: String,
    @ColumnInfo (name = "description") val description: String,
    @ColumnInfo (name = "rating") val rating: Double = 0.0,
    var isFavorite: Boolean = false
) : Parcelable
