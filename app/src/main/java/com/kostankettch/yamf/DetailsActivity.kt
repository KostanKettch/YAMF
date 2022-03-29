package com.kostankettch.yamf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val cinema = intent.extras?.get("cinema") as Cinema

        details_toolbar.title = cinema.title
        details_description.text = cinema.description
        details_poster.setImageResource(cinema.poster)

    }



}