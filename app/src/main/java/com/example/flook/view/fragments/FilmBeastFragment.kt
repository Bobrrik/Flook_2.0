package com.example.flook.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.example.flook.data.BaseFilm
import com.example.flook.view.rv_adapters.FilmAdapters
import com.example.flook.domain.Films
import com.example.flook.MainActivity
import com.example.flook.databinding.FragmentFilmBeastBinding


class FilmBeastFragment : Fragment() {
    lateinit var binding: FragmentFilmBeastBinding
    private lateinit var filmAdapters: FilmAdapters
    lateinit var favoritesList: List<Films>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmBeastBinding.inflate(inflater, container, false)
        return binding.root
    }

    init {
        exitTransition = Slide(Gravity.START).apply { duration = 800;mode = Slide.MODE_OUT }
        reenterTransition = Slide(Gravity.START).apply { duration = 800; }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AdapterBase()
        StartWindows()
    }

    fun AdapterBase() {

        favoritesList = BaseFilm().BaseFilms()
        //= favoritesList = Base().BaseFilms()

        favoritesList = favoritesList.filter { it.beast == true }
        //    favoritesList = favoritesList.filter { it.title == "fff"}

        binding.recyclerView.apply {
            filmAdapters = FilmAdapters(object : FilmAdapters.OnItemClickListener {
                override fun click(films: Films) {
                    (requireActivity() as MainActivity).launchDetailsFragment(films)
                }
            })
            adapter = filmAdapters
            layoutManager = LinearLayoutManager(requireContext())
        }

        filmAdapters.addItems(favoritesList)
    }

    fun StartWindows() {
        if (favoritesList.size == 0) {
            binding.blocking.alpha = 1f
        } else {
            binding.blocking.alpha = 0f
        }
    }
}