package com.example.flordechocolate

import android.app.Application
import com.example.flordechocolate.di.dataSourceModule
import com.example.flordechocolate.di.repoModule
import com.example.flordechocolate.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FlorApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext( this@FlorApp)
            modules(listOf(dataSourceModule,repoModule, viewModelModule))
        }
    }
}