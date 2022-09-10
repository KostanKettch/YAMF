package com.kostankettch.yamf.data

import android.content.ContentValues
import android.database.Cursor
import com.kostankettch.yamf.domain.Cinema

class MainRepository(databaseHelper: DatabaseHelper) {
    private val sqlDb = databaseHelper.readableDatabase
    private lateinit var cursor: Cursor

    fun putToDb(cinema: Cinema) {
        val cv = ContentValues()
        cv.apply {
            put(DatabaseHelper.COLUMN_TITLE, cinema.title)
            put(DatabaseHelper.COLUMN_POSTER, cinema.poster)
            put(DatabaseHelper.COLUMN_DESCRIPTION, cinema.description)
            put(DatabaseHelper.COLUMN_RATING, cinema.rating)
        }
        sqlDb.insert(DatabaseHelper.TABLE_NAME, null, cv)
    }

    fun getAllFromDB(): List<Cinema> {
        cursor = sqlDb.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME}", null)
        val result = mutableListOf<Cinema>()
        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(1)
                val poster = cursor.getString(2)
                val description = cursor.getString(3)
                val rating = cursor.getDouble(4)

                result.add(Cinema(title, poster, description, rating))
            } while (cursor.moveToNext())
        }
        return result
    }
}