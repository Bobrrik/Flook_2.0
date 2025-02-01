package com.example.flook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flook.databinding.FragmentFilmRvBinding

class FilmFragment_item : Fragment() {

    lateinit var binding : FragmentFilmRvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFilmRvBinding.inflate(inflater , container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setFilmDetails()

    }
    private fun setFilmDetails(){
        val film = arguments?.get("film") as Films

        binding.detailsToolbar.title = film.title
        binding.detailsDescription.text = film.textLong
        binding.detailsPoster.setImageResource(film.poster)

    }
}