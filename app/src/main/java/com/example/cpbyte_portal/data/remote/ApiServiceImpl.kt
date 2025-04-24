package com.example.cpbyte_portal.data.remote

import com.example.cpbyte_portal.domain.service.ApiService
import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class ApiServiceImpl(private val client: HttpClient): ApiService {
    private val BASE_URL="https://cpbyte-student-portal.onrender.com/api"

    override suspend fun login(loginRequest: LoginRequest):LoginResponse{
        return client.post("$BASE_URL/v1/auth/login"){
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
    }
}
