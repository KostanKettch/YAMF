package com.kostankettch.yamf.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kostankettch.yamf.R
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.view.rv_viewholders.MovieViewHolder

class MovieListRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items = mutableListOf<Cinema>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    clickListener.click(items[position])

                }
            }
        }
    }

    fun addItems(list: List<Cinema>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun click(cinema: Cinema)
    }
}