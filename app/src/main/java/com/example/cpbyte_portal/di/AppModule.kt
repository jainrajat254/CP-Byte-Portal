package com.example.cpbyte_portal.di

import com.example.cpbyte_portal.domain.service.ApiService
import com.example.cpbyte_portal.data.remote.ApiServiceImpl
import com.example.cpbyte_portal.data.repository.LoginRepositoryImpl
import com.example.cpbyte_portal.domain.repository.LoginRepository
import org.koin.dsl.module

val appModule = module {
    single<ApiService> { ApiServiceImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}