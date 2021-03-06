package com.kostankettch.yamf


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.title
    private val poster = itemView.poster
    private val description = itemView.description

    fun bind(cinema: Cinema) {
        title.text = cinema.title
        description.text = cinema.description
        Glide.with(itemView)
            .load(cinema.poster)
            .centerCrop()
            .into(poster)
    }

}