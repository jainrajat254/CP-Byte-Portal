package com.example.cpbyte_portal.di

import com.example.cpbyte_portal.domain.service.ApiService
import com.example.cpbyte_portal.data.remote.ApiServiceImpl
import com.example.cpbyte_portal.data.repository.AuthRepositoryImpl
import com.example.cpbyte_portal.data.repository.CoordinatorRepositoryImpl
import com.example.cpbyte_portal.data.repository.EventRepositoryImpl
import com.example.cpbyte_portal.data.repository.SettingsRepositoryImpl
import com.example.cpbyte_portal.data.repository.UserRepositoryImpl
import com.example.cpbyte_portal.domain.repository.AuthRepository
import com.example.cpbyte_portal.domain.repository.CoordinatorRepository
import com.example.cpbyte_portal.domain.repository.EventRepository
import com.example.cpbyte_portal.domain.repository.SettingsRepository
import com.example.cpbyte_portal.domain.repository.UserRepository
import com.example.cpbyte_portal.util.SharedPrefsManager
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SharedPrefsManager(get()) }

    single<ApiService> { ApiServiceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }
    single<CoordinatorRepository> { CoordinatorRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }

    viewModel { AuthViewModel(get()) }
}