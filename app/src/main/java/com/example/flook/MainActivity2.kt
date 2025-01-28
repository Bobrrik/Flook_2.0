package com.example.flook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.flook.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityMain2Binding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val film = intent.extras?.get("film") as Films

        binding.detailsToolbar.title = film.title
        binding.detailsDescription.text = film.textLong//в класс вынесется в последствии
        binding.detailsPoster.setImageResource(film.poster)

    }
}