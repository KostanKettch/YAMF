package com.kostankettch.yamf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private  lateinit var cinema: Cinema


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCinemaDetails()

        details_fab_favorites.setOnClickListener {
            if (false.also { cinema.isFavorite = it }) {
                details_fab_favorites.setImageResource(R.drawable.ic_round_star_24)
                cinema.isFavorite = true
            } else {
                details_fab_favorites.setImageResource(R.drawable.ic_round_star_border_24)
                cinema.isFavorite = false
            }
        }
        details_fab_share.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film ${cinema.title} \n \n ${cinema.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))

        }
    }

    private fun setCinemaDetails() {
        val cinema = arguments?.get("cinema") as Cinema

        details_toolbar.title = cinema.title
        details_description.text = cinema.description
        details_poster.setImageResource(cinema.poster)

        details_fab_favorites.setImageResource(
            if (cinema.isFavorite) R.drawable.ic_round_star_24
            else R.drawable.ic_round_star_border_24
        )
    }
}