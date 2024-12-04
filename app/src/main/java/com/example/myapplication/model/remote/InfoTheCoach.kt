package com.example.myapplication.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoTheCoach(
    @Json(name = "imei") val imei: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "id") val id: Int,
    @Json(name = "status") val status: String,
    @Json(name = "product_code") val productCode: String,
    @Json(name = "data") val data: String,
    @Json(name = "refunded") val refunded: String,
    @Json(name = "created_at") val createdAt:String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "is_test") val isTest: String,
    @Json(name = "email") val email: String,
    @Json(name = "order_type") val orderType: String,
    @Json(name = "channel_id") val channelId: String,
    @Json(name = "campaign") val campaign:String,
    @Json(name = "payment_type") val paymentType: String,
    @Json(name = "promotion_code") val promotionCode: String,
    @Json(name = "name") val name: String
)