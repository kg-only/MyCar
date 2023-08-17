package com.example.mycar

import android.app.Application
import com.example.mycar.di.Module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(baseContext)
            modules(Module.module)
        }
    }
}