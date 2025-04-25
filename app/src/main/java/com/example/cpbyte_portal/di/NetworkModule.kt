package com.example.cpbyte_portal.di

import com.example.cpbyte_portal.util.SharedPrefsManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        val prefs: SharedPrefsManager = get()
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
            }
            install(DefaultRequest) {
                if (!url.encodedPath.contains("/v1/auth/login")) {
                    prefs.getToken()?.let { token ->
                        headers.append("Authorization", "Bearer $token")
                    }
                }
            }
        }
    }
}