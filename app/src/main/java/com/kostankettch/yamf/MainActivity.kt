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
        imgButtons()
    }

    fun menuButtons() {
        button1.setOnClickListener {
            Toast.makeText(this, R.string.button1Toast, Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener {
            Toast.makeText(this, R.string.button2Toast, Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            Toast.makeText(this, R.string.button3Toast, Toast.LENGTH_SHORT).show()
        }
        button4.setOnClickListener {
            Toast.makeText(this, R.string.button4Toast, Toast.LENGTH_SHORT).show()
        }
        button5.setOnClickListener {
            Toast.makeText(this, R.string.button5Toast, Toast.LENGTH_SHORT).show()
        }
    }
    fun imgButtons() {
        image1.setOnClickListener {
            Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
        }
        image2.setOnClickListener {
            Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
        }
        image3.setOnClickListener {
            Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
        }
        image4.setOnClickListener {
            Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
        }
        image5.setOnClickListener {
            Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
        }
    }
}