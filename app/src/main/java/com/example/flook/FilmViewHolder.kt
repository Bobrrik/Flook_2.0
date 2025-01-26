package com.example.flook

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.textTittle)
    private val poster = itemView.findViewById<ImageView>(R.id.imageAvatar)
    private val description = itemView.findViewById<TextView>(R.id.textDescription)


    fun bind(films: Films) {
        title.text = films.title
        poster.setImageResource(films.poster)
        description.text = films.textLong
    }
}