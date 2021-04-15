package com.pumi.app.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pumi.app.databinding.FragmentPhumBinding
import com.seanghay.statusbar.statusBar

class PhumFragment : Fragment() {

    private var _binding: FragmentPhumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPhumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        statusBar.light(false).color(Color.TRANSPARENT)
    }
}