package com.example.myapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.remote.BaseResponse
import com.example.myapplication.model.remote.NetworkResult
import com.example.myapplication.repository.TheCoachRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivateStateVM : ViewModel() {

    private val theCoachRepository = TheCoachRepository()
    val activateResponseLiveData: MutableLiveData<NetworkResult<BaseResponse>> =
        theCoachRepository.activateResponseLiveData
    var imei: String = ""
    var phoneNumber: String = ""

    fun getData(isNetworkConnect: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            theCoachRepository.activate(isNetworkConnect, imei, phoneNumber)
        }
    }

}