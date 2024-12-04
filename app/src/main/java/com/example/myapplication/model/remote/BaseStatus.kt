package com.example.myapplication.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseStatus(
    @Json(name = "status") val status: Boolean = false,
    @Json(name = "msg") val message: String = ""
)

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "data") val data:T? = null
) : BaseStatus()