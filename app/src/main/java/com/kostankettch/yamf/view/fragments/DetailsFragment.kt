package com.kostankettch.yamf.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kostankettch.yamf.R
import com.kostankettch.yamf.data.ApiConstants
import com.kostankettch.yamf.databinding.FragmentDetailsBinding
import com.kostankettch.yamf.domain.Cinema

class DetailsFragment : Fragment() {
    private lateinit var cinema: Cinema
    private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCinemaDetails()

        binding.detailsFabFavorites.setOnClickListener {
            if (!cinema.isFavorite) {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_star_24)
                cinema.isFavorite = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_star_border_24)
                cinema.isFavorite = false
            }
        }
        binding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${cinema.title} \n \n ${cinema.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))

        }
    }

    private fun setCinemaDetails() {
        cinema = arguments?.get("cinema") as Cinema

        binding.detailsToolbar.title = cinema.title

        Glide.with(this)
            .load(ApiConstants.IMAGE_URL + "w780" + cinema.poster)
            .centerCrop()
            .into(binding.detailsPoster)
        binding.detailsDescription.text = cinema.description
        binding.detailsFabFavorites.setImageResource(
            if (cinema.isFavorite) R.drawable.ic_round_star_24
            else R.drawable.ic_round_star_border_24
        )
    }
}