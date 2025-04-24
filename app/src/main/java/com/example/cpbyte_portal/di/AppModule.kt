package com.example.cpbyte_portal.di

import com.example.cpbyte_portal.data.remote.ApiService
import com.example.cpbyte_portal.data.remote.ApiServiceImpl
import com.example.cpbyte_portal.service.provideHttpClient
import org.koin.dsl.module

val appModule = module {
    single { provideHttpClient() }
    single<ApiService> { ApiServiceImpl(get()) }
}