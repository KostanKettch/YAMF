package com.kostankettch.yamf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuButtons()
    }

    fun menuButtons() {
        button1.setOnClickListener {
            Toast.makeText(this, "something", Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener {
            Toast.makeText(this, "something else", Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            Toast.makeText(this, "something more", Toast.LENGTH_SHORT).show()
        }
        button4.setOnClickListener {
            Toast.makeText(this, "something else more", Toast.LENGTH_SHORT).show()
        }
        button5.setOnClickListener {
            Toast.makeText(
                this,
                "And Now for Something Completely Different",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}