package com.kostankettch.yamf


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
        settingsBtn()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()

    }

    fun launchDetailsFragment(cinema: Cinema) {
        val bundle = Bundle()
        bundle.putParcelable("cinema", cinema)
        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {
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
    }

    private fun settingsBtn() {
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun imgButtons() {
        topBar.setOnClickListener {
            when (it.id) {
                R.id.image1 -> {
                    Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.image2 -> {
                    Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.image3 -> {
                    Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.image4 -> {
                    Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.image5 -> {
                    Toast.makeText(this, R.string.imageDescription, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


    }

}