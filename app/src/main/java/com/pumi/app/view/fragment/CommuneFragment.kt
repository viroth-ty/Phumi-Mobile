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
import com.pumi.app.databinding.FragmentCommuneBinding
import com.pumi.app.listener.HandleListener
import com.pumi.app.utils.Constant
import com.pumi.app.utils.navigateSafe
import com.pumi.app.view.epoxy.ProvinceController
import com.pumi.app.viewmodel.CommuneViewModel

class CommuneFragment : Fragment() {

    private val viewModel: CommuneViewModel by viewModels()
    private var _binding: FragmentCommuneBinding? = null
    private val binding get() = _binding!!
    private lateinit var controller: ProvinceController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCommuneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val navController = findNavController()
        val id = arguments?.getString(Constant.Bundle.id)
        val name = arguments?.getString(Constant.Bundle.name)

        binding.toolbarTitle.text = name

        initAction(districtId = id!!)
        initView(navController = navController)
        initObservable(navController = navController)
    }

    private fun initAction(districtId: String) {
        if (viewModel.phum.value!!.isEmpty()) {
            viewModel.getCommune(districtId = districtId)
        }
    }

    private fun initView(navController: NavController) {

        binding.lottie.setAnimation(R.raw.loading)
        binding.lottie.loop(true)

        binding.backButton.setOnClickListener {
            navController.popBackStack()
        }
        controller = ProvinceController(context = requireContext(), requireContext().getColor(R.color.flame), object : HandleListener {
            override fun onItemClick(item: Phum) {
                super.onItemClick(item)
                val bundle = bundleOf(
                    Constant.Bundle.id to item.id,
                    Constant.Bundle.name to item.fullNameKM
                )
                navController.navigateSafe(R.id.action_communeFragment_to_villageFragment, bundle)
            }
        },
            textButton = requireContext().getString(R.string.see_village),
            navController = findNavController()
        )
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