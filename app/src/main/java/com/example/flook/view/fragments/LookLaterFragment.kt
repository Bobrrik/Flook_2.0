package com.example.flook.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flook.R
import com.example.flook.databinding.FragmentLookLaterBinding

class LookLaterFragment : Fragment() {
    lateinit var binding: FragmentLookLaterBinding

//    init {
//        exitTransition = Slide(Gravity.START).apply { duration = 800;mode = Slide.MODE_OUT }
//        reenterTransition = Slide(Gravity.START).apply { duration = 800; }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLookLaterBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_look_later, container, false)
    }
}