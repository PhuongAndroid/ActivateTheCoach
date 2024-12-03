package com.example.myapplication.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.MainActivity
import com.example.myapplication.helper.ConnectivityRepository
import com.example.myapplication.helper.IMEIHelper
import com.example.myapplication.helper.replaceFragmentToBackStack
import com.example.myapplication.model.remote.NetworkResult.Error
import com.example.myapplication.model.remote.NetworkResult.Loading
import com.example.myapplication.model.remote.NetworkResult.Success
import com.example.myapplication.viewmodels.ActivateStateVM
import com.example.myapplication.views.dialog.showNotifyDialog
import com.example.thecoach.R
import com.example.thecoach.databinding.FragmentActivateStateBinding

class ActivateStateFragment : Fragment() {

    private val viewModel: ActivateStateVM by viewModels()
    private val binding: FragmentActivateStateBinding by lazy {
        FragmentActivateStateBinding.inflate(layoutInflater)
    }
    private val connectRepository: ConnectivityRepository by lazy {
        ConnectivityRepository(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectRepository
        viewModel.imei = getImeiDevice()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEvents()
        handleObservers()
        initViews()
    }

    private fun initViews() {
        binding.txtImei.text = getImeiDevice()
        binding.txtValidPhoneNumber.isVisible = false
        binding.btnActivate.isEnabled = false
    }

    private fun registerEvents() {
        binding.editText.apply {
            doAfterTextChanged {
                if (length() >= 10) {
                    val isPhoneNumber = isVietnamesePhoneNumber(text.toString())
//                    binding.txtValidPhoneNumber.isVisible = !isPhoneNumber
//                    binding.btnActivate.isEnabled = isPhoneNumber
                    binding.btnActivate.isEnabled = true
                } else {
                    binding.txtValidPhoneNumber.isVisible = false
                    binding.btnActivate.isEnabled = false
                }
            }
        }

        binding.btnActivate.setOnClickListener {
            viewModel.getData(
                isNetworkConnect = connectRepository._isConnected.value
            )
        }
    }

    private fun handleObservers() {
        viewModel.activateResponseLiveData.observe(viewLifecycleOwner) { data ->
            when (data) {
                is Loading -> {}
                is Error ->
                    showErrorDialog(data.message)

                is Success ->
                    showSuccessDialog()
            }
        }
    }

    private fun showErrorDialog(errorMessage: String?) {
        showNotifyDialog(
            mContext = requireContext(),
            src = ContextCompat.getDrawable(requireContext(), R.drawable.ic_remove),
            title = getString(R.string.activate_fail_title),
            note = errorMessage,
            textButton = getString(R.string.activate_fail_button),
            isSuccess = false,
        ) {
            binding.editText.setText("")
            binding.btnActivate.isEnabled = false
        }
    }

    private fun showSuccessDialog() {
        showNotifyDialog(
            mContext = requireContext(),
            src = ContextCompat.getDrawable(requireContext(), R.drawable.ic_check),
            title = getString(R.string.note_activated),
            note = getString(R.string.note_activated),
            textButton = getString(R.string.activated_button),
            isSuccess = true,
        ) {
            (requireActivity() as MainActivity).replaceFragmentToBackStack(
                R.id.container,
                ActivatedFragment.newInstance()
            )
        }
    }

    private fun isVietnamesePhoneNumber(number: String): Boolean {
        val regex = Regex("/(?:\\+84|0084|0)[235789][0-9]{1,2}[0-9]{7}(?:[^\\d]+|\$)/g")
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