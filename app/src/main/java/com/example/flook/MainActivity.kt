package com.example.flook

import android.content.Intent
import android.os.Bundle
import android.telecom.Call.Details
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var filmAdapters: FilmAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val rV = binding.recyclerView

        val filmDataBase = listOf(
            Films("fff", "sdfsdf", R.drawable.film1),
            Films("fff", "sdfsdf", R.drawable.film2),
            Films("fff", "sdfsdf", R.drawable.film3),
            Films("fff", "sdfsdf", R.drawable.film4),
            Films("fff", "sdfsdf", R.drawable.film5),
            Films("fff", "sdfsdf", R.drawable.film6),
            Films("fff", "sdfsdf", R.drawable.film7),
            Films("fff", "sdfsdf", R.drawable.film8)
        )

        rV.apply {
            filmAdapters = FilmAdapters(object : FilmAdapters.OnItemClickListener {
                override fun click(films: Films) {
                    val intent = Intent(this@MainActivity,MainActivity2::class.java)
                    startActivity(intent)
                }
            })
            adapter = filmAdapters
            layoutManager = LinearLayoutManager(this@MainActivity)
            //декоратор

        }
        filmAdapters.addItems(filmDataBase)
    }
}