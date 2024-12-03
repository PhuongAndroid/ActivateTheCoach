package com.example.myapplication.helper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.remote.BaseResponse
import com.example.myapplication.model.remote.NetworkResult
import com.squareup.moshi.Moshi

inline fun <T> MutableLiveData<NetworkResult<T>>.postSimpled(callback: () -> NetworkResult<T>) {
    try {
        postValue(callback())
    } catch (e: retrofit2.HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        if (errorBody != null) {
            try {
                val messageResponse =
                    Moshi.Builder().build().adapter(BaseResponse::class.java).fromJson(errorBody)
                if (messageResponse != null) {
                    postValue(NetworkResult.Error(message = messageResponse.message))
                }
            } catch (e: Exception) {
                Log.d("Hackere232", e.stackTraceToString())
                postValue(NetworkResult.Error(message = e.message))
            }
        }
    } catch (e: Exception) {
        Log.d("Hackere232", e.stackTraceToString())
        postValue(NetworkResult.Error(message = "Unknow"))
    }
}