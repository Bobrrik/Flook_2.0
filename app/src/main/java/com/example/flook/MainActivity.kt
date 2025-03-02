package com.example.flook

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flook.Fragment.FilmBeastFragment
import com.example.flook.Fragment.FilmHomeFragment
import com.example.flook.Fragment.Film_ItemFragment
import com.example.flook.Fragment.FilterFragment
import com.example.flook.Fragment.LookLaterFragment
import com.example.flook.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.Executors
import kotlin.math.hypot
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var backPressed = 0L
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Анимация появления
        Executors.newSingleThreadExecutor().execute {
            while (true) {
                if (binding.blocking.isAttachedToWindow) {
                    runOnUiThread {
                        startWindows()
                    }
                    return@execute
                }
            }
        }

        iniNavigation()

        fragmentHome()
    }

    fun launchDetailsFragment(films: Films) {
        val bundle = Bundle()
        bundle.putParcelable("film", films)
        val fragment = Film_ItemFragment()
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
                R.id.buttonHome -> {
                    reval(binding.navigator)
                    fragmentHome()
                }

                R.id.buttonFilter -> {
                    reval(binding.navigator)
                    fragmentFilter()
                }

                R.id.buttonFavorite -> {
                    reval(binding.navigator)
                    fragmentBeast()
                }

                R.id.buttonLookLater -> {
                    reval(binding.navigator)
                    fragmentLookLater()
                }
            }
            true
        }
    }

    fun reval(view: View) {
        val x: Int = view.x.roundToInt() + view.width / 2
        val y: Int = view.y.roundToInt() + view.height / 2
        val startRad = 0f
        val endRad = hypot(
            binding.fragmentPlaceholder.width.toDouble(),
            binding.fragmentPlaceholder.height.toDouble()
        ).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(
            binding.fragmentPlaceholder,
            x,
            y,
            startRad,
            endRad
        )
        anim.duration = 500
        anim.start()
    }

    fun startWindows() {
        val x: Int = binding.anim.x.roundToInt() + binding.anim.width / 2
        val y: Int = binding.anim.y.roundToInt() + binding.anim.height / 2
        val startRad = hypot(
            binding.blocking.width.toDouble(), binding.blocking.height.toDouble()
        ).toFloat()
        val endRad = 0f

        val anim = ViewAnimationUtils.createCircularReveal(binding.blocking, x, y, startRad, endRad)

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                binding.blocking.visibility = View.GONE
            }
        })

        anim.duration = 1000
        anim.startDelay = 2000
        anim.start()
    }

    fun fragmentHome() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentPlaceholder.id, FilmHomeFragment())
            .addToBackStack(null)
            .commit()
    }

    fun fragmentBeast() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlaceholder.id, FilmBeastFragment())
            .addToBackStack(null)
            .commit()
    }

    fun fragmentLookLater() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlaceholder.id, LookLaterFragment())
            .addToBackStack(null)
            .commit()
    }

    fun fragmentFilter() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlaceholder.id, FilterFragment())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TIME_INTERVAL = 2000
    }
}