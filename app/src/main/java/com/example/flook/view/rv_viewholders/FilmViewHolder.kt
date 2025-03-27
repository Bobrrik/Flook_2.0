package com.example.flook.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.flook.databinding.ItemFilmBinding
import com.example.flook.domain.Films

class FilmViewHolder(private val bindingItem : ItemFilmBinding) : RecyclerView.ViewHolder(bindingItem.root) {

    private val title = bindingItem.textTittle
    private val poster = bindingItem.imageAvatar
    private val description = bindingItem.textDescription
    private val rating = bindingItem.ratingRV


    fun bind(films: Films) {
        title.text = films.title
        poster.setImageResource(films.poster)
        description.text = films.textLong
        rating.setProgress(films.rating)
    }
}