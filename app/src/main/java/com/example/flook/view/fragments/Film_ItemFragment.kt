package com.example.flook.view.fragments

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Slide
import com.bumptech.glide.Glide
import com.example.flook.R
import com.example.flook.data.ApiConstants
import com.example.flook.data.entity.Films
import com.example.flook.databinding.FragmentItemFilmBinding
import com.example.flook.viewmodel.Film_ItemFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Film_ItemFragment : Fragment() {
    private lateinit var film: Films
    private lateinit var binding: FragmentItemFilmBinding
    private val scope = CoroutineScope(Dispatchers.IO)
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
        binding = FragmentItemFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmDetails()
        ClickOn()
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
        )
    }

    private fun String.handleSingleQuote(): String {
        return this.replace("'", "")
    }

    fun savePoster(bitmap: Bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValue = ContentValues().apply {
                put(MediaStore.Images.Media.TITLE, film.title.handleSingleQuote())
                put(MediaStore.Images.Media.DISPLAY_NAME, film.title.handleSingleQuote())
                put(MediaStore.Images.Media.CONTENT_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
                put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/FilmsSearchApp")
            }
            val contentResolver = requireActivity().contentResolver
            val uri =
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue)
            val outputStream = contentResolver.openOutputStream(uri!!)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream!!)
            outputStream?.close()
        } else {
            @Suppress("DEPRECATION")
            MediaStore.Images.Media.insertImage(
                requireActivity().contentResolver,
                bitmap,
                film.title.handleSingleQuote(),
                film.textLong.handleSingleQuote()
            )
        }
    }

    fun performAsyncLoadOfPoster() {
        if (!checkPermission()) {
            requestPermission()
            return
        }
        MainScope().launch {
            binding.progressBar.isVisible = true
            val job = scope.async {
                viewModel.LodePoster(ApiConstants.IMAGES_URL + "original" + film.poster)
            }
            savePoster(job.await())
            Snackbar.make(binding.root, R.string.downloaded_to_gallery, Snackbar.LENGTH_LONG)
                .setAction(R.string.open) {
                val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.type = "image/*"
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                .show()
            binding.progressBar.isVisible = false
        }
    }

    fun ClickOn() {
        binding.saveFab.setOnClickListener {
            performAsyncLoadOfPoster()
        }

        binding.postFab.setOnClickListener {
            // поделиться
        }

        binding.beastFab.setOnClickListener {
            if (film.beast) {
                binding.beastFab.setImageResource(R.drawable.baseline_favorite_no)
                film.beast = false
                viewModel.swapBeast(film)    //   <---- придумать как передать фильм для изменения избраности
                //viewModel.swapBeast(requireContext(), binding.detailsToolbar.title.toString(), 0)
            } else {
                binding.beastFab.setImageResource(R.drawable.baseline_favorite_yes)
                film.beast = true
                //   viewModel.swapBeast(film)
                //viewModel.swapBeast(requireContext(), binding.detailsToolbar.title.toString(), 1)
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