package com.example.cpbyte_portal

import android.app.Application
import com.example.cpbyte_portal.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CPBytePortal:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CPBytePortal)
            modules(appModule)
        }
    }
}