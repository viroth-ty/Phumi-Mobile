package com.pumi.app.view.location

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.pumi.app.R
import com.pumi.app.databinding.FragmentLocationBinding
import com.pumi.app.listener.LocationListener
import com.pumi.app.view.fragment.LocationViewModel
import com.seanghay.statusbar.statusBar

class LocationFragment : Fragment() {

    private val viewModel: LocationViewModel by activityViewModels()
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.light(false).color(Color.TRANSPARENT)
    }

    override fun onResume() {
        super.onResume()
        statusBar.light(false).color(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        initObserve()
    }

    private fun initObserve() {

        viewModel.location.observe(viewLifecycleOwner, { location ->

            println("location changed ${location}")

            when (location) {
                LocationListener.PERMISSION_GRANTED -> {
                    replaceFragmentOfTag("allow_fragment") { AllowFragment.newInstance() }
                }

                LocationListener.PERMISSION_DENIED -> {
                    replaceFragmentOfTag("forbid_fragment") { ForbidFragment.newInstance() }
                }

                else -> {
                    replaceFragmentOfTag("forbid_fragment") { ForbidFragment.newInstance() }
                }
            }
        })

    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            replaceFragmentOfTag("allow_fragment") { AllowFragment.newInstance() }
        } else {
            replaceFragmentOfTag("forbid_fragment") { ForbidFragment.newInstance() }
        }
    }

    private fun <T : Fragment> replaceFragmentOfTag(tag: String, factory: () -> T) {
        val primaryFragment = findFragmentByTagOrElse(tag, factory)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, primaryFragment, tag)
            setPrimaryNavigationFragment(primaryFragment)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Fragment> findFragmentByTagOrElse(tag: String, factory: () -> T): T {
        return childFragmentManager.findFragmentByTag(tag) as? T ?: factory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
