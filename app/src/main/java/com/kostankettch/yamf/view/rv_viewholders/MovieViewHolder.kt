package com.kostankettch.yamf.view.rv_viewholders


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kostankettch.yamf.domain.Cinema
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.title
    private val poster = itemView.poster
    private val description = itemView.description
    private val rating = itemView.rating

    fun bind(cinema: Cinema) {
        title.text = cinema.title
        description.text = cinema.description
        Glide.with(itemView)
            .load(cinema.poster)
            .centerCrop()
            .into(poster)
        rating.setProgress((cinema.rating*10).toInt())
    }

}