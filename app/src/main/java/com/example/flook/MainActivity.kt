package com.example.flook

import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.flook.databinding.ActivityMainBinding
import com.example.flook.domain.Films
import com.example.flook.view.fragments.FilmBeastFragment
import com.example.flook.view.fragments.FilmHomeFragment
import com.example.flook.view.fragments.Film_ItemFragment
import com.example.flook.view.fragments.LookLaterFragment
import com.example.flook.view.fragments.SettingsFragment
import kotlin.math.hypot
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var backPressed = 0L
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        Executors.newSingleThreadExecutor().execute {     // Анимация появления
//            while (true) {
//                if (binding.blocking.isAttachedToWindow) {
//                    runOnUiThread {
//                        startWindows()
//                    }
//                    return@execute
//        }    }    }

        fragmentOpen(FilmHomeFragment())
        clickOn()
    }

    fun clickOn() {
        iniNavigation()
    }

    fun launchDetailsFragment(films: Films) {
        val bundle = Bundle()
        bundle.putParcelable("film", films)
        val fragment = Film_ItemFragment()
        fragment.arguments = bundle

        fragmentOpen(fragment)
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
        binding.navigator.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.buttonHome -> {
                    reval(binding.navigator)
                    fragmentOpen(FilmHomeFragment())
                }

                R.id.buttonFilter -> {
                    reval(binding.navigator)
                    fragmentOpen(SettingsFragment())
                }

                R.id.buttonFavorite -> {
                    reval(binding.navigator)
                    fragmentOpen(FilmBeastFragment())
                }

                R.id.buttonLookLater -> {
                    reval(binding.navigator)
                    fragmentOpen(LookLaterFragment())
                }
            }
            true
        }
    }

    fun reval(view: View) {   //  анимация нажатия и переходов
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

//    fun startWindows() {
//        val x: Int = binding.anim.x.roundToInt() + binding.anim.width / 2
//        val y: Int = binding.anim.y.roundToInt() + binding.anim.height / 2
//        val startRad = hypot(
//            binding.blocking.width.toDouble(), binding.blocking.height.toDouble()
//        ).toFloat()
//
//        val endRad = 0f
//
//        val anim = ViewAnimationUtils.createCircularReveal(binding.blocking, x, y, startRad, endRad)
//
//        anim.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator) {
//                binding.blocking.visibility = View.GONE
//            }
//        })
//
//        anim.duration = 1000
//        anim.startDelay = 2000
//        anim.start()
//    }

    fun fragmentOpen(frag: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentPlaceholder.id, frag)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TIME_INTERVAL = 2000
    }
}