package com.example.cpbyte_portal.domain.usecase


import android.util.Base64
import org.json.JSONObject


class IsTokenExpiredUseCase {
    operator fun invoke(jwt: String?): Boolean {
        if (jwt.isNullOrBlank()) return true

        return try {
            val parts = jwt.split(".")
            if (parts.size != 3) return true // Invalid token

            val payloadJson = String(Base64.decode(parts[1], Base64.URL_SAFE))
            val payload = JSONObject(payloadJson)

            val exp = payload.optLong("exp", 0L)
            val currentTime = System.currentTimeMillis() / 1000

            exp < currentTime
        } catch (e: Exception) {
            true // Fail-safe: treat as expired
        }
    }
}
