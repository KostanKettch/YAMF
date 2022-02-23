package com.kostankettch.yamf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, R.string.favorites, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, R.string.watch_later, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.collections -> {
                    Toast.makeText(this, R.string.collections, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        imgButtons()
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