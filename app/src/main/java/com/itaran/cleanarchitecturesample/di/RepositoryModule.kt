package com.itaran.cleanarchitecturesample.di

import com.itaran.data.api.RetrofitProvider
import com.itaran.data.api.MainApi
import com.itaran.data.api.RefreshTokenApi
import com.itaran.data.api.RequestsProxy
import com.itaran.data.repository.MainItemRepositoryImpl
import com.itaran.data.repository.UpdateTokenRepository
import com.itaran.domain.repository.MainItemRepository
import com.itaran.data.prefs.IPrefs
import com.itaran.data.prefs.PreferencesManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit

fun repositoryModule() = module {

    single<RetrofitProvider> {
        RetrofitProvider(prefs = get())
    }

    single<Retrofit> {
        get<RetrofitProvider>().retrofit
    }

    single<IPrefs> {
        PreferencesManager.initializeShared(androidApplication())
    }

    single<RequestsProxy> {
        RequestsProxy(prefs = get(), updateToken = get())
    }

    single<MainItemRepository> {
        MainItemRepositoryImpl(api = get(), proxy = get(), prefs = get())
    }

    single<MainApi> {
        val retrofit = get<Retrofit>()
        retrofit.create(MainApi::class.java)
    }

    single<RefreshTokenApi> {
        val retrofit = get<Retrofit>()
        retrofit.create(RefreshTokenApi::class.java)
    }

    single<UpdateTokenRepository> {
        UpdateTokenRepository(api = get(), prefs = get())
    }
}