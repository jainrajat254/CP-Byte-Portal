package com.example.cpbyte_portal.domain.service

import com.example.cpbyte_portal.domain.model.LoginRequest
import com.example.cpbyte_portal.domain.model.LoginResponse


interface ApiService {

    suspend fun login(loginRequest:LoginRequest): LoginResponse

}



