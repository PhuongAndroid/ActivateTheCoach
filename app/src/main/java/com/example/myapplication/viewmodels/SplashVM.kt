package com.example.myapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.remote.BaseResponse
import com.example.myapplication.model.remote.BaseStatus
import com.example.myapplication.model.remote.InfoTheCoach
import com.example.myapplication.model.remote.NetworkResult
import com.example.myapplication.repository.TheCoachRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashVM : ViewModel() {
    var isActivated: Boolean = false
    private val theCoachRepository = TheCoachRepository()
    val theCoachInfoLiveData: MutableLiveData<NetworkResult<BaseResponse<InfoTheCoach>>> =
        theCoachRepository.theCoachInfoLiveData
    var imei = ""

    fun getTheCoachInfo(isNetworkConnect: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            theCoachRepository.getTheCoachInfo(isNetworkConnect, imei)
        }
    }
}