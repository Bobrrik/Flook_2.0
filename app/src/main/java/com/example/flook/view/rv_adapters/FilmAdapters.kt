package com.example.flook.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flook.view.rv_viewholders.FilmViewHolder
import com.example.flook.databinding.ItemFilmBinding
import com.example.flook.domain.Films

class FilmAdapters(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: ItemFilmBinding
     val item = mutableListOf<Films>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(item[position])
                binding.itemRV.setOnClickListener {
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