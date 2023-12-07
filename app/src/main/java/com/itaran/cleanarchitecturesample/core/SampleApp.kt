package com.itaran.cleanarchitecturesample.core

import android.app.Application
import com.itaran.cleanarchitecturesample.di.repositoryModule
import com.itaran.cleanarchitecturesample.di.usecasesModule
import com.itaran.cleanarchitecturesample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SampleApp)
            modules(listOf(repositoryModule(), usecasesModule(), viewModelModule()))
        }
    }
}