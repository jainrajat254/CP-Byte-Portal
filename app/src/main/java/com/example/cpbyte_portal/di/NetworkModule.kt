package com.example.cpbyte_portal.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module{
    single{
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
//            install(HttpTimeout){
//                requestTimeoutMillis=15_000
//            }
//            install(DefaultRequest) {
//                TokenProvider.token?.let {
//                    headers.append("Authorization", "Bearer $it")
//                }
//            }
        }
    }
}