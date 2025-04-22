package com.example.cpbyte_portal.di

import com.example.cpbyte_portal.service.ApiService
import com.example.cpbyte_portal.service.ApiServiceImpl
import com.example.cpbyte_portal.service.provideHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideHttpClient() }
    single<ApiService> { ApiServiceImpl(get()) }
}