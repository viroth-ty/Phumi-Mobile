package com.pumi.app.view.location

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pumi.app.R
import com.pumi.app.data.model.ResolveBody
import com.pumi.app.databinding.FragmentAllowBinding
import com.pumi.app.view.epoxy.lottie
import com.pumi.app.view.epoxy.province
import com.pumi.app.view.epoxy.title
import com.pumi.app.viewmodel.LocationViewModel

class AllowFragment : Fragment() {

    private var _binding: FragmentAllowBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LocationViewModel by activityViewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAllowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getCurrentLocation()
        initView()
        initObserve()
    }

    private fun getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                viewModel.getCurrentLocation(resolveBody = ResolveBody(lat = location.latitude.toString(), lng = location.longitude.toString()))
            }
            return
        }
    }

    private fun initView() {
        binding.lottie.setAnimation(R.raw.loading)
    }

    private fun initObserve() {
        
        viewModel.loading.observe(viewLifecycleOwner, {
            if(it) {
                binding.lottie.visibility = View.VISIBLE
            } else {
                binding.lottie.pauseAnimation()
                binding.lottie.visibility = View.GONE
            }
        })

        viewModel.resolve.observe(viewLifecycleOwner, { resolve ->

            binding.recyclerview.withModels {

                lottie {
                    id("lottie")
                    source(R.raw.map_location)
                }

                title {
                    id("title")
                    title(resources.getString(R.string.your_current_location_is))
                }

                province {
                    id("province_${resolve.province.code}")
                    khmer(resolve.province.km)
                    english(resolve.province.en)
                    buttonText(resources.getString(R.string.see_district))
                    color(requireContext().getColor(R.color.jade))
                    view(View.GONE)
                }

                province {
                    id("district_${resolve.district.code}")
                    khmer(resolve.district.km)
                    english(resolve.district.en)
                    buttonText(resources.getString(R.string.see_commune))
                    color(requireContext().getColor(R.color.chestnut))
                    view(View.GONE)
                }

                province {
                    id("commune_${resolve.commune.code}")
                    khmer(resolve.commune.km)
                    english(resolve.commune.en)
                    buttonText(resources.getString(R.string.see_village))
                    color(requireContext().getColor(R.color.flame))
                    view(View.GONE)
                }

            }
            binding.recyclerview.requestModelBuild()
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(): AllowFragment {
            return AllowFragment()
        }
    }
}