package com.example.flook

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.example.flook.databinding.FragmentFilmBeastBinding


class FilmBeastFragment : Fragment() {
    lateinit var binding: FragmentFilmBeastBinding

    init {
        exitTransition = Slide(Gravity.START).apply { duration = 800;mode = Slide.MODE_OUT }
        reenterTransition = Slide(Gravity.START).apply { duration = 800; }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StartWindows()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmBeastBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun StartWindows() {
        if (binding.recyclerView.size == 0) {
            binding.blocking.alpha = 1f
            binding.searchView.alpha = 0f
        }
    }
}