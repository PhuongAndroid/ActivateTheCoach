package com.example.myapplication.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse(
    @Json(name = "status")
    val status: Boolean = false,
    @Json(name = "msg")
    val message: String = ""
)