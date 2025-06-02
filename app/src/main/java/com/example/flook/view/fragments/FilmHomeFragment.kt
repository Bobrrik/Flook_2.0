package com.example.flook.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Scene
import androidx.transition.Slide
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.flook.MainActivity
import com.example.flook.data.entity.Films
import com.example.flook.databinding.FragmentHomeBinding
import com.example.flook.databinding.HomeScreenBinding
import com.example.flook.view.rv_adapters.FilmAdapters
import com.example.flook.viewmodel.HomeFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class FilmHomeFragment : Fragment() {
    lateinit var bindingFragment: HomeScreenBinding
    lateinit var binding: FragmentHomeBinding
    private lateinit var filmAdapters: FilmAdapters
    lateinit var scope: CoroutineScope
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

//    init {
//        exitTransition = Slide(Gravity.START).apply { duration = 800;mode = Slide.MODE_OUT }
//        reenterTransition = Slide(Gravity.START).apply { duration = 800; }
//    }

    private var filmDataBase = listOf<Films>()
        set(value) {
            if (field == value) return
            field = value
            filmAdapters.addItems(field)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        bindingFragment = HomeScreenBinding.inflate(inflater, container, false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scene = Scene(bindingFragment.homeFragmentRoot, binding.root) // смена сцены

        scope = CoroutineScope(Dispatchers.IO).also { scope ->
            scope.launch {
                viewModel.filmsListLiveData.collect {
                    withContext(Dispatchers.Main) {
                        filmAdapters.addItems(it)
                        filmDataBase = it
                    }
                }
            }
        }

        TransitionManager.go(scene, AnimatedOpen())
        AdapterBase()
        initPullToRefresh()
        ClickL()
    }

    fun AnimatedOpen(): TransitionSet {                                             // Анимации обьектов по отдельности
        val searSlide = Slide(Gravity.TOP).addTarget(binding.searchView)
        val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(binding.recyclerView)
        val customTransition = TransitionSet().apply {
            duration = 500
            addTransition(searSlide)
            addTransition(recyclerSlide)
        }
        return customTransition
    }

    fun ClickL() {
        binding.searchView.setOnClickListener { binding.searchView.isIconified = false }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    filmAdapters.addItems(filmDataBase)
                    return true
                }

                val result = filmDataBase.filter {
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }

                filmAdapters.addItems(result)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
        })
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

        filmAdapters.addItems(filmDataBase)
    }

    private fun initPullToRefresh() {
        scope.launch {
            for (element in viewModel.progressBarShow) {
                launch(Dispatchers.Main) { binding.progressBar.isVisible = element }
            }
        }

        binding.refresh.setOnRefreshListener {
            filmAdapters.item.clear()
            viewModel.newPage()
            binding.refresh.isRefreshing = false
        }
    }

    override fun onStop() {
        super.onStop()
        scope.cancel()
    }
}

