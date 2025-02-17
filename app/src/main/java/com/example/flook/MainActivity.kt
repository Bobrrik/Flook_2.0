package com.example.flook

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flook.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var backPressed = 0L
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        iniNavigation()

        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentPlaceholder.id, FilmHomeFragment())
            .addToBackStack(null)
            .commit()
    }

    fun launchDetailsFragment(films: Films) {
        val bundle = Bundle()
        bundle.putParcelable("film", films)
        val fragment = FilmFragment_item()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlaceholder.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else Toast.makeText(this, "Вы точно хотите выйти?", Toast.LENGTH_SHORT).show()
            backPressed = System.currentTimeMillis()
        } else super.onBackPressed()
    }

    fun iniNavigation() {

        val snackbar = Snackbar.make(binding.main, "переходик", Snackbar.LENGTH_SHORT)
        snackbar.setAction("Понял!") {
            Toast.makeText(this, "Сколько переходов то?!", Toast.LENGTH_SHORT).show()
        }

        binding.navigator.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.buttonSetting -> {
                    snackbar.show()
                }

                R.id.buttonFilter -> {
                    snackbar.show()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.fragmentPlaceholder.id, FilmBeastFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
            true
        }
    }

    companion object {
        const val TIME_INTERVAL = 2000
    }
}