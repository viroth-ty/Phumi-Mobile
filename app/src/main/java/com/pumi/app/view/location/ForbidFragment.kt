package com.pumi.app.view.location

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.pumi.app.R
import com.pumi.app.databinding.FragmentForbidBinding
import com.pumi.app.listener.LocationListener
import com.pumi.app.utils.Constant
import com.pumi.app.viewmodel.LocationViewModel

class ForbidFragment : Fragment() {

    private val viewModel: LocationViewModel by activityViewModels()
    private var _binding: FragmentForbidBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentForbidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.lottie.setAnimation(R.raw.location_search)
        binding.lottie.playAnimation()

        binding.permissionButton.setOnClickListener {
            requestPermission()
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION), Constant.Location.PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            Constant.Location.PERMISSION_REQUEST_CODE -> {
                if (permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel.setLocation(locationListener = LocationListener.PERMISSION_GRANTED)
                } else {
                    viewModel.setLocation(locationListener = LocationListener.PERMISSION_DENIED)
                }
                return
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ForbidFragment {
            return ForbidFragment()
        }
    }

}