package com.example.flook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.example.flook.databinding.FragmentRvSelectionBinding
import com.example.flook.databinding.MergeHomeScreenContentBinding
import java.util.Locale

class FilmSelectionFragment : Fragment() {
    lateinit var  bindingFather : FragmentRvSelectionBinding
    lateinit var binding: MergeHomeScreenContentBinding
    private lateinit var filmAdapters: FilmAdapters
    val filmDataBase = listOf(
        Films("Фильм 1", "dddddd", R.drawable.film1),
        Films("fff", "sdfsdf", R.drawable.film2),
        Films("Фильм 3", "sdfsdf", R.drawable.film3),
        Films("Фильм 4", "sdfsdf", R.drawable.film4),
        Films("Фильм 5", "sdfsdf", R.drawable.film5),
        Films("Фильм 6", "sdfsdf", R.drawable.film6),
        Films("Фильм 7", "sdfsdf", R.drawable.film7),
        Films("Фильм 8", "sdfsdf", R.drawable.film8)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MergeHomeScreenContentBinding.inflate(inflater,container,false)
        bindingFather = FragmentRvSelectionBinding.inflate(inflater, container, false)
        return bindingFather.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rV = binding.recyclerView
        val scene = Scene.getSceneForLayout(bindingFather.homeFragmentRoot,R.layout.merge_home_screen_content,requireContext())

        TransitionManager.go(scene)

        rV.apply {
            filmAdapters = FilmAdapters(object : FilmAdapters.OnItemClickListener {
                override fun click(films: Films) {
                    (requireActivity() as MainActivity).launchDetailsFragment(films)
                }
            })
            adapter = filmAdapters
            layoutManager = LinearLayoutManager(requireContext())

        }

        filmAdapters.addItems(filmDataBase)

        ClickL()
    }

    fun ClickL() {
        binding.searchView.setOnClickListener { binding.searchView.isIconified = false }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if  (newText.isEmpty()) {
                    filmAdapters.addItems(filmDataBase)
                    return true
                }
                val result = filmDataBase.filter {
                    it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                filmAdapters.addItems(result)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
        })

    }

}