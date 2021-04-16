package com.pumi.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.pumi.app.R
import com.pumi.app.data.model.Phum
import com.pumi.app.databinding.FragmentProvinceBinding
import com.pumi.app.listener.HandleListener
import com.pumi.app.utils.Constant
import com.pumi.app.utils.navigateSafe
import com.pumi.app.view.epoxy.ProvinceController
import com.seanghay.statusbar.statusBar

class ProvinceFragment : Fragment() {


    private val viewModel: ProvinceViewModel by viewModels()
    private var _binding: FragmentProvinceBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var provinceController: ProvinceController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProvinceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = findNavController()

        initAction()
        initView(navController = navController)
        initObservable(navController = navController)
    }

    private fun initAction() {
        if (viewModel.phum.value!!.isEmpty()) {
            viewModel.getProvince()
        }
    }

    private fun initView(navController: NavController) {

        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.loop(true)

        provinceController =
            ProvinceController(context = requireContext(), requireContext().getColor(R.color.jade), object : HandleListener {
                override fun onItemClick(item: Phum) {
                    super.onItemClick(item)
                    val bundle = bundleOf(
                        Constant.Bundle.id to item.id,
                        Constant.Bundle.name to item.fullNameKM
                    )
                    navController.navigateSafe(R.id.action_provinceFragment_to_districtFragment,
                        bundle)
                }
            }, textButton = requireContext().getString(R.string.see_district))
        binding.provinceRecyclerview.setController(provinceController)
    }

    private fun initObservable(navController: NavController) {

        viewModel.phum.observe(viewLifecycleOwner, {
            provinceController.submit(it)
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                binding.lottie.visibility = View.VISIBLE
                binding.lottie.playAnimation()
            } else {
                binding.lottie.visibility = View.GONE
            }
        })
    }
}