// networkModule.kt
package com.example.cpbyte_portal.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.HttpTimeout
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module


import io.ktor.client.plugins.api.*
import io.ktor.http.*

val AuthHeaderPlugin = createClientPlugin("AuthHeaderPlugin") {
    onRequest { request, _ ->
        val token = TokenProvider.token
        if (!request.url.encodedPath.endsWith("/login") && !token.isNullOrBlank()) {
            request.headers.append(HttpHeaders.Authorization, "Bearer $token")
        }
    }
}

// NetworkModule.kt
fun provideHttpClient(): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15_000
        }
        install(Logging) {
            level = LogLevel.ALL
        }

        install(AuthHeaderPlugin)
    }
}

val networkModule = module {
    single<HttpClient> { provideHttpClient() }
}
