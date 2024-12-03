package com.example.myapplication.api

import com.example.myapplication.model.remote.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TheCoachApiService {
    // activate
    @FormUrlEncoded
    @POST("api/kidphone/active")
    suspend fun activate(
        @Field("imei") imei: String
    ) : BaseResponse
    // information the coach
    @FormUrlEncoded
    @POST("api/kidphone/infoTheCoach")
    suspend fun infoTheCoach(
        @Field("imei") imei: String
    )  : BaseResponse
    // activate the coach
    @FormUrlEncoded
    @POST("api/kidphone/activeTheCoach")
    suspend fun activeTheCoach(
        @Field("imei") imei: String,
        @Field("phone") phone: String
    ) : BaseResponse
}