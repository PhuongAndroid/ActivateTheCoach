package com.example.myapplication.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.myapplication.viewmodels.InformationVM
import com.example.thecoach.databinding.FragmentActivatedBinding

class InformationFragment : Fragment() {

    private val viewModel: InformationVM by viewModels()
    private val binding: FragmentActivatedBinding by lazy {
        FragmentActivatedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.imei = arguments?.getString(IMEI) ?: ""
        viewModel.phoneNumber = arguments?.getString(PHONE) ?: ""
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
        binding.txtImei.text = viewModel.imei
        binding.txtPhoneNumber.text = viewModel.phoneNumber
    }

    private fun registerEvents() {
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().supportFragmentManager.popBackStackImmediate(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
        }
    }

    companion object {
        const val IMEI = "iemi"
        const val PHONE = "phone_number"
        fun newInstance(imei: String, phoneNumber: String): InformationFragment {
            val fragment = InformationFragment()
            val bundle = Bundle()
            bundle.apply {
                putString(IMEI, imei)
                putString(PHONE, phoneNumber)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}