package com.example.cpbyte_portal.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(private val context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    companion object {
        private const val JWT_TOKEN = "jwt_token"
    }

    fun saveToken(token: String) {
        prefs.edit().putString(JWT_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return prefs.getString(JWT_TOKEN, null)
    }

    fun clearToken() {
        prefs.edit().remove(JWT_TOKEN).apply()
    }
}
