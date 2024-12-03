package com.example.myapplication.helper

import android.content.ContentResolver
import android.provider.Settings
import android.util.Log

object IMEIHelper {
    fun getIMEICode(contentResolver: ContentResolver): String? {
        return try {
            val imeiCode = Settings.System.getString(contentResolver, "fise_imei_code")
            if (imeiCode != null) {
                Log.d("IMEIHelper", "IMEI Code: $imeiCode")
            } else {
                Log.w("IMEIHelper", "IMEI Code not found in system settings.")
            }
            imeiCode
        } catch (e: Exception) {
            Log.e("IMEIHelper", "Error fetching IMEI Code: ", e)
            null
        }
    }
}