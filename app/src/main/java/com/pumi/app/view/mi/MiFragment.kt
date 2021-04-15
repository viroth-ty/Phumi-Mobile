package com.pumi.app.view.mi

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pumi.app.R
import com.pumi.app.databinding.FragmentMiBinding
import com.seanghay.statusbar.statusBar

class MiFragment : Fragment() {

    private var _binding: FragmentMiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.light(false).color(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

    }

    private fun initView() {
        binding.lottie.setAnimation(R.raw.scooter_riding)
        binding.lottie.playAnimation()
    }

    override fun onResume() {
        super.onResume()
        statusBar.light(false).color(Color.TRANSPARENT)
    }

}