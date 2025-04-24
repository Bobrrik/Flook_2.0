package com.example.flook.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.example.flook.MainActivity
import com.example.flook.databinding.FragmentFilmBeastBinding
import com.example.flook.domain.Films
import com.example.flook.view.rv_adapters.FilmAdapters
import com.example.flook.viewmodel.HomeFragmentViewModel


class FilmBeastFragment : Fragment() {
    lateinit var binding: FragmentFilmBeastBinding
    private lateinit var filmAdapters: FilmAdapters
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)

    }
    private var favoritesList = listOf<Films>()
        set(value) {
            if (field == value) return
            val filteredList = value.filter { it.beast == true }
            field = filteredList
            filmAdapters.addItems(field)
        }

//          favoritesList = favoritesList.filter { it.beast == true }

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

        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer<List<Films>> {
            favoritesList = it
        })

        AdapterBase()
     //   StartWindows()
    }

    fun AdapterBase() {

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

    fun StartWindows() {   // реализовать метод
        if (filmAdapters.equals(null)) {
            binding.blocking.alpha = 1f
        } else {
            binding.blocking.alpha = 0f
        }
    }
}