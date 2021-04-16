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
import com.pumi.app.databinding.FragmentDistrictBinding
import com.pumi.app.listener.HandleListener
import com.pumi.app.utils.Constant
import com.pumi.app.utils.navigateSafe
import com.pumi.app.view.epoxy.ProvinceController
import com.seanghay.statusbar.statusBar

class DistrictFragment : Fragment() {

    private val viewModel: DistrictViewModel by viewModels()
    private var _binding: FragmentDistrictBinding? = null
    private val binding get() = _binding!!
    private lateinit var provinceController: ProvinceController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDistrictBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val navController = findNavController()

        val id = arguments?.getString(Constant.Bundle.id)
        val name = arguments?.getString(Constant.Bundle.name)

        binding.toolbarTitle.text = name

        initAction(provinceId = id!!)
        initView(navController = navController)
        initObservable(navController = navController)
    }


    private fun initAction(provinceId: String) {
        if (viewModel.phum.value!!.isEmpty()) {
            viewModel.getDistrict(provinceId = provinceId)
        }
    }

    private fun initView(navController: NavController) {

        binding.lottie.setAnimation(R.raw.loading)
        binding.backButton.setOnClickListener {
            navController.popBackStack()
        }
        provinceController =
            ProvinceController(context = requireContext(), requireContext().getColor(R.color.chestnut), object : HandleListener {
                override fun onItemClick(item: Phum) {
                    super.onItemClick(item)
                    val bundle = bundleOf(
                        Constant.Bundle.id to item.id,
                        Constant.Bundle.name to item.fullNameKM
                    )
                    navController.navigateSafe(R.id.action_districtFragment_to_communeFragment,
                        bundle)
                }
            }, textButton = requireContext().getString(R.string.see_commune))
        binding.recyclerview.setController(provinceController)
    }

    private fun initObservable(navController: NavController) {

        viewModel.phum.observe(viewLifecycleOwner, {
            provinceController.submit(it)
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                binding.lottie.visibility = View.VISIBLE
                binding.lottie.playAnimation()
            }
            else {
                binding.lottie.visibility = View.GONE
            }
        })
    }
}