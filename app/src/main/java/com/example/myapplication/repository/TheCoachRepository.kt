package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.ApiClient
import com.example.myapplication.helper.postSimpled
import com.example.myapplication.model.remote.BaseResponse
import com.example.myapplication.model.remote.BaseStatus
import com.example.myapplication.model.remote.InfoTheCoach
import com.example.myapplication.model.remote.NetworkResult

class TheCoachRepository {

    private val apiService = ApiClient.apiService
    val activateResponseLiveData: MutableLiveData<NetworkResult<BaseStatus>> =
        MutableLiveData()
    val theCoachInfoLiveData: MutableLiveData<NetworkResult<BaseResponse<InfoTheCoach>>> =
        MutableLiveData()
    val sendIMEILiveData: MutableLiveData<NetworkResult<BaseStatus>> =
        MutableLiveData()

    suspend fun sendIMEI(isNetworkConnect: Boolean, imei: String) {
        if (isNetworkConnect) {
            sendIMEILiveData.postSimpled { apiService.activate(imei) }
        }
    }

    suspend fun activate(isNetworkConnect: Boolean, imei: String, phone: String) {
        activateResponseLiveData.postValue(NetworkResult.Loading())
        if (isNetworkConnect) {
            activateResponseLiveData.postSimpled { apiService.activeTheCoach(imei, phone) }
        } else {
            activateResponseLiveData.postValue(NetworkResult.Error("No Internet connection !"))
        }
    }

    suspend fun getTheCoachInfo(isNetworkConnect: Boolean, imei: String) {
        theCoachInfoLiveData.postValue(NetworkResult.Loading())
        if (isNetworkConnect) {
            theCoachInfoLiveData.postSimpled { apiService.infoTheCoach(imei) }
        } else
            theCoachInfoLiveData.postValue(NetworkResult.Error("No Internet connection !"))
    }
}