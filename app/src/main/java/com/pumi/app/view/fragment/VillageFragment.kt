package com.pumi.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.pumi.app.R
import com.pumi.app.databinding.FragmentVillageBinding
import com.pumi.app.utils.Constant
import com.pumi.app.view.epoxy.VillageController

class VillageFragment : Fragment() {

    private val viewModel: VillageViewModel by viewModels()
    private var _binding: FragmentVillageBinding? = null
    private val binding get() = _binding!!
    private lateinit var controller: VillageController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentVillageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val navController = findNavController()

        val id = arguments?.getString(Constant.Bundle.id)
        initAction(communeId = id!!)
        initView(navController = navController)
        initObservable(navController = navController)

    }

    private fun initAction(communeId: String) {
        if (viewModel.phum.value!!.isEmpty()) {
            viewModel.getCommune(communeId = communeId)
        }
    }

    private fun initView(navController: NavController) {
        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.loop(true)

        controller = VillageController()
        binding.recyclerview.setController(controller)
    }

    private fun initObservable(navController: NavController) {

        viewModel.phum.observe(viewLifecycleOwner, {
            controller.submit(it)
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}