// Interceptor.kt
package com.example.cpbyte_portal.interceptor

import com.example.cpbyte_portal.util.SharedPrefsManager
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpHeaders

fun addAuthTokenInterceptor(prefs: SharedPrefsManager) = { request: HttpRequestBuilder ->
    val token = prefs.getToken()
    if (!token.isNullOrBlank()) {
        request.headers.append(HttpHeaders.Authorization, "Bearer $token")
    }
}
