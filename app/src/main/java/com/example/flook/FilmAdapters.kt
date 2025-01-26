package com.example.flook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flook.databinding.ItemFilmBinding

class FilmAdapters(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val item = mutableListOf<Films>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(item[position])
                holder.itemView..setOnClickListener {
                    clickListener.click(item[position])
                }
            }
        }
    }

    override fun getItemCount() = item.size

    fun addItems(list: List<Films>) {
        item.clear()
        item.addAll(list)
        notifyDataSetChanged()
    }
    interface OnItemClickListener {
        fun click(films: Films)
    }
}