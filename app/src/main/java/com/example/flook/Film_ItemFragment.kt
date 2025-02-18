package com.example.flook

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.example.flook.databinding.FragmentFilmRvBinding

class Film_ItemFragment : Fragment() {
    private lateinit var film: Films
    private lateinit var binding: FragmentFilmRvBinding

    init {
        exitTransition = Slide(Gravity.START).apply { duration = 800;mode = Slide.MODE_OUT }
        reenterTransition = Slide(Gravity.START).apply { duration = 800; }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmRvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ClickOn()
        setFilmDetails()
    }

    fun ClickOn() {
        binding.postFab.setOnClickListener {
            // поделиться 26.8
        }

        binding.beastFab.setOnClickListener {
            if (film.beast) {
                binding.beastFab.setImageResource(R.drawable.baseline_favorite_no)
                film.beast = false
              //  Base().favoriteUp(film.title)
            } else {
                binding.beastFab.setImageResource(R.drawable.baseline_favorite_yes)
                film.beast = true
               // Base().favoriteUp(film.title)
            }
        }
    }

    private fun setFilmDetails() {
        film = arguments?.get("film") as Films

        binding.detailsToolbar.title = film.title
        binding.detailsDescription.text = film.textLong
        binding.detailsPoster.setImageResource(film.poster)

        binding.beastFab.setImageResource(
            if (film.beast) R.drawable.baseline_favorite_yes
            else R.drawable.baseline_favorite_no
        )
    }
}