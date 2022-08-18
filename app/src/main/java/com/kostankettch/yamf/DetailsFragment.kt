package com.kostankettch.yamf

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kostankettch.yamf.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private lateinit var cinema: Cinema
    private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding= FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCinemaDetails()

        binding.detailsFabFavorites.setOnClickListener {
            if (cinema.isFavorite ) {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_star_24)
                cinema.isFavorite = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_star_border_24)
                cinema.isFavorite = false
            }
        }
        binding.detailsFabFavorites.setOnClickListener {
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

        binding.detailsToolbar.title = cinema.title
        binding.detailsDescription.text = cinema.description
        binding.detailsPoster.setImageResource(cinema.poster)

        binding.detailsFabFavorites.setImageResource(
            if (cinema.isFavorite) R.drawable.ic_round_star_24
            else R.drawable.ic_round_star_border_24
        )
    }
}