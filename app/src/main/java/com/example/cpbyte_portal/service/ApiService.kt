package com.example.cpbyte_portal.service

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ApiService {


}

class ApiServiceImpl(private val client: HttpClient):ApiService{
    
}

