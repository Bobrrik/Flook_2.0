package com.example.flook.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flook.data.ApiConstants
import com.example.flook.databinding.ItemFilmBinding
import com.example.flook.domain.Films

class FilmViewHolder(private val bindingItem : ItemFilmBinding) : RecyclerView.ViewHolder(bindingItem.root) {

    private val title = bindingItem.textTittle
    private val poster = bindingItem.imageAvatar
    private val description = bindingItem.textDescription
    private val rating = bindingItem.ratingRV


    fun bind(films: Films) {
        title.text = films.title

        Glide.with(itemView)
            .load(ApiConstants.IMAGES_URL+"w342"+films.poster)
            .centerCrop()
            .into(poster)

        description.text = films.textLong
        rating.setProgress(films.rating)
    }
}