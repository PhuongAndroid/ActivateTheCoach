package com.example.myapplication.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.MainActivity
import com.example.myapplication.helper.ConnectivityRepository
import com.example.myapplication.helper.IMEIHelper
import com.example.myapplication.helper.replaceFragmentToBackStack
import com.example.myapplication.model.remote.NetworkResult
import com.example.myapplication.viewmodels.SplashVM
import com.example.thecoach.R
import com.example.thecoach.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val viewModel: SplashVM by viewModels()
    private val binding: FragmentSplashBinding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.imei = getImeiDevice()
        viewModel.getTheCoachInfo(
            isNetworkConnect =
            ConnectivityRepository(requireContext())._isConnected.value)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtImeiCode.text = getImeiDevice()
        registerEvents()
        handleObservers()
    }

    private fun getImeiDevice(): String {
        // Lấy ContentResolver từ context
        val contentResolver = requireActivity().contentResolver

        // Gọi hàm lấy IMEI
        val imei = IMEIHelper.getIMEICode(contentResolver)

        // Kiểm tra giá trị IMEI
        return if (imei != null) "Device IMEI: $imei" else "Unable to retrieve IMEI code."
    }

    private fun registerEvents() {
        binding.itemTheCoach.cardActivate.setOnClickListener {
            if (viewModel.isActivated) {
                (requireActivity() as MainActivity).replaceFragmentToBackStack(
                    R.id.container,
                    ActivatedFragment.newInstance()
                )
            } else {
                (requireActivity() as MainActivity).replaceFragmentToBackStack(
                    R.id.container,
                    ActivateStateFragment.newInstance()
                )
            }
        }

        binding.itemSupport.cardSupport.setOnClickListener {
            // go to zalo support
        }
    }

    private fun handleObservers() {
        viewModel.theCoachInfoLiveData.observe(viewLifecycleOwner) { data ->
            when (data) {
                is NetworkResult.Loading -> binding.viewLoading.isVisible = true
                is NetworkResult.Error -> {
                    binding.viewLoading.isVisible = false
                    viewModel.isActivated = false
                }
                is NetworkResult.Success -> {
                    binding.viewLoading.isVisible = false
                    viewModel.isActivated = true
                }
            }
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}