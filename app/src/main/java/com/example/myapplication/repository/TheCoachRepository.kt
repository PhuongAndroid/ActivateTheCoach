package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.ApiClient
import com.example.myapplication.helper.postSimpled
import com.example.myapplication.model.remote.BaseResponse
import com.example.myapplication.model.remote.NetworkResult

class TheCoachRepository {

    val apiService = ApiClient.apiService
    val activateResponseLiveData: MutableLiveData<NetworkResult<BaseResponse>> = MutableLiveData()

    suspend fun fetchResult(isNetworkConnect: Boolean, imei: String, phone: String) {
        activateResponseLiveData.value = NetworkResult.Loading()
        if (isNetworkConnect) {
            activateResponseLiveData.postSimpled { NetworkResult(apiService.activeTheCoach(imei, phone)) }
        } else {
            activateResponseLiveData.value = NetworkResult.Error("No Internet connection !")
        }
    }
}