package com.example.cpbyte_portal.di

import com.example.cpbyte_portal.domain.service.ApiService
import com.example.cpbyte_portal.data.remote.ApiServiceImpl
import com.example.cpbyte_portal.data.repository.LoginRepositoryImpl
import com.example.cpbyte_portal.domain.repository.LoginRepository
import com.example.cpbyte_portal.util.SharedPrefsManager
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    single { SharedPrefsManager(get()) }
    single<ApiService> { ApiServiceImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    viewModel { AuthViewModel(get()) }
}