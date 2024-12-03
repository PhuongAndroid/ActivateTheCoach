package com.example.myapplication.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.myapplication.helper.IMEIHelper
import com.example.thecoach.databinding.FragmentActivateStateBinding

class ActivateStateFragment : Fragment() {

    private val binding: FragmentActivateStateBinding by lazy {
        FragmentActivateStateBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEvents()
        initViews()
    }

    private fun initViews() {
        binding.txtImei.text = getImeiDevice()
        binding.txtValidPhoneNumber.isVisible = false
        binding.btnActivate.isEnabled = false
    }

    private fun registerEvents() {
        binding.txtImei.apply {
            val isPhoneNumber = isVietnamesePhoneNumber(text.toString())
            doAfterTextChanged {
                binding.txtValidPhoneNumber.isVisible = !isPhoneNumber
                binding.btnActivate.isEnabled = isPhoneNumber
            }
        }
    }

    private fun isVietnamesePhoneNumber(number: String): Boolean {
        val regex = Regex("/(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b/")
        return regex.matches(number)
    }

    private fun getImeiDevice(): String {
        // Lấy ContentResolver từ context
        val contentResolver = requireActivity().contentResolver

        // Gọi hàm lấy IMEI
        val imei = IMEIHelper.getIMEICode(contentResolver)

        // Kiểm tra giá trị IMEI
        return if (imei != null) "Device IMEI: $imei" else "Unable to retrieve IMEI code."
    }

    companion object {
        fun newInstance() = ActivateStateFragment()
    }
}