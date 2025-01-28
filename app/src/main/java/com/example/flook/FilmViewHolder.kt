package com.example.flook

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flook.databinding.ItemFilmBinding

class FilmViewHolder(private val bindingItem : ItemFilmBinding) : RecyclerView.ViewHolder(bindingItem.root) {

    private val title = bindingItem.textTittle
    private val poster = bindingItem.imageAvatar
    private val description = bindingItem.textDescription


    fun bind(films: Films) {
        title.text = films.title
        poster.setImageResource(films.poster)
        description.text = films.textLong
    }
}