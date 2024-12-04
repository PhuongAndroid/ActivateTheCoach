package com.example.myapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.remote.BaseStatus
import com.example.myapplication.model.remote.NetworkResult
import com.example.myapplication.repository.TheCoachRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivateStateVM : ViewModel() {

    private val theCoachRepository = TheCoachRepository()
    val activateResponseLiveData: MutableLiveData<NetworkResult<BaseStatus>> =
        theCoachRepository.activateResponseLiveData
    val sendIMEILiveData: MutableLiveData<NetworkResult<BaseStatus>> =
        theCoachRepository.sendIMEILiveData
    var imei: String = ""
    var phoneNumber: String = ""

    fun onActivate(isNetworkConnect: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            // activate the coach
            theCoachRepository.activate(isNetworkConnect, imei, phoneNumber)
        }
    }

    fun sendIMEI(isNetworkConnect: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            // send imei -> save activation date
            theCoachRepository.sendIMEI(isNetworkConnect, imei)
        }
    }

}