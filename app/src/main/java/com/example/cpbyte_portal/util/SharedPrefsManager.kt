package com.example.cpbyte_portal.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.cpbyte_portal.domain.model.ProfileResponse
import kotlinx.serialization.json.Json
import androidx.core.content.edit

class SharedPrefsManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    companion object {
        private const val JWT_TOKEN = "jwt_token"
        private const val PROFILE_DATA = "profile_data"
    }

    fun saveToken(token: String) {
        prefs.edit(commit = true) { putString(JWT_TOKEN, token) }
    }

    fun getToken(): String? {
        return prefs.getString(JWT_TOKEN, null)
    }

    fun clearToken() {
        prefs.edit { remove(JWT_TOKEN) }
    }

    fun saveProfile(profile: ProfileResponse) {
        val profileJson = json.encodeToString(ProfileResponse.serializer(), profile)
        Log.d("SharedPrefs", "Profile JSON: $profileJson")
        prefs.edit { putString(PROFILE_DATA, profileJson) }
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
        prefs.edit { remove(PROFILE_DATA) }
    }

    fun clearAll() {
        prefs.edit { clear() }
        Log.d("SharedPrefs", "All prefs cleared")
    }
}
