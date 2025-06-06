package com.example.cpbyte_portal.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.cpbyte_portal.domain.model.ProfileResponse
import kotlinx.serialization.json.Json

class SharedPrefsManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    companion object {
        private const val JWT_TOKEN = "jwt_token"
        private const val PROFILE_DATA = "profile_data"
    }

    // JWT Token
    fun saveToken(token: String) {
        prefs.edit().putString(JWT_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return prefs.getString(JWT_TOKEN, null)
    }

    fun clearToken() {
        prefs.edit().remove(JWT_TOKEN).apply()
    }

    fun saveProfile(profile: ProfileResponse) {
        val profileJson = json.encodeToString(ProfileResponse.serializer(), profile)
        prefs.edit().putString(PROFILE_DATA, profileJson).apply()
    }

    fun getProfile(): ProfileResponse? {
        val jsonString = prefs.getString(PROFILE_DATA, null) ?: return null
        return try {
            json.decodeFromString(ProfileResponse.serializer(), jsonString)
        } catch (e: Exception) {
            null
        }
    }

    fun clearProfile() {
        prefs.edit().remove(PROFILE_DATA).apply()
    }

    fun clearAll() {
        prefs.edit().clear().apply()
        Log.d("SharedPrefs", "All prefs cleared")
    }
}
