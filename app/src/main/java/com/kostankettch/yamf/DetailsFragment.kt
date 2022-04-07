package com.kostankettch.yamf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {


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
    }

    private fun setCinemaDetails() {
        val cinema = arguments?.get("cinema") as Cinema

        details_toolbar.title = cinema.title
        details_description.text = cinema.description
        details_poster.setImageResource(cinema.poster)

    }


}

