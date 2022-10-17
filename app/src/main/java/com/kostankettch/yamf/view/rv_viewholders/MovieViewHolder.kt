package com.kostankettch.yamf.view.rv_viewholders


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kostankettch.yamf.data.ApiConstants
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.databinding.MovieItemBinding

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
private val movieItemBinding = MovieItemBinding.bind(itemView)

    private val title = movieItemBinding.title
    private val poster = movieItemBinding.poster
    private val description = movieItemBinding.description
    private val rating = movieItemBinding.rating

    fun bind(cinema: Cinema) {
        title.text = cinema.title
        description.text = cinema.description
        Glide.with(itemView)
            .load(ApiConstants.IMAGE_URL + "w342" + cinema.poster)
            .centerCrop()
            .into(poster)
        rating.setProgress((cinema.rating*10).toInt())
    }

}