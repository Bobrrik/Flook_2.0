package com.example.flook.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Slide
import com.bumptech.glide.Glide
import com.example.flook.R
import com.example.flook.data.ApiConstants
import com.example.flook.databinding.FragmentFilmRvBinding
import com.example.flook.domain.Films
import com.example.flook.viewmodel.Film_ItemFragmentViewModel

class Film_ItemFragment : Fragment() {
    private lateinit var film: Films
    private lateinit var binding: FragmentFilmRvBinding
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(Film_ItemFragmentViewModel::class.java)
    }

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

        setFilmDetails()
        ClickOn()
    }

    fun ClickOn() {
        binding.postFab.setOnClickListener {
            // поделиться
        }

        binding.beastFab.setOnClickListener {
            if (film.beast) {
                binding.beastFab.setImageResource(R.drawable.baseline_favorite_no)
                film.beast = false
                viewModel.swapBeast(requireContext(), binding.detailsToolbar.title.toString(), 0)
            } else {
                binding.beastFab.setImageResource(R.drawable.baseline_favorite_yes)
                film.beast = true
                viewModel.swapBeast(requireContext(), binding.detailsToolbar.title.toString(), 1)
            }
        }
    }

    private fun setFilmDetails() {
        film = arguments?.get("film") as Films

        binding.detailsToolbar.title = film.title
        binding.detailsDescription.text = film.textLong

        Glide.with(this)
            .load(ApiConstants.IMAGES_URL + "w342" + film.poster)
            .into(binding.detailsPoster)

        binding.beastFab.setImageResource(
            if (film.beast) R.drawable.baseline_favorite_yes
            else R.drawable.baseline_favorite_no
        )
    }
}