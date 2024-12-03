package com.example.myapplication.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.helper.IMEIHelper
import com.example.myapplication.helper.replaceFragmentToBackStack
import com.example.thecoach.R
import com.example.thecoach.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val binding: FragmentSplashBinding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
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
            (requireActivity() as MainActivity).replaceFragmentToBackStack(
                R.id.container,
                ActivateStateFragment.newInstance()
            )
        }

        binding.itemSupport.cardSupport.setOnClickListener {
            // go to zalo support
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}