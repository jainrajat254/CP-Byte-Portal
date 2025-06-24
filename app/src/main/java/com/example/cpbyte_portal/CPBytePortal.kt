package com.example.cpbyte_portal

import android.app.Application
import com.example.cpbyte_portal.di.TokenProvider
import com.example.cpbyte_portal.di.appModule
import com.example.cpbyte_portal.di.networkModule
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CPBytePortal : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CPBytePortal)
            allowOverride(true)
            modules(appModule, networkModule)
        }

        // Load persisted token at startup
        val prefs = SharedPrefsManager(this)
        TokenProvider.token = prefs.getToken()
    }
}
