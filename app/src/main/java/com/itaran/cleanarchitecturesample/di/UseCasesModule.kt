package com.itaran.cleanarchitecturesample.di

import com.itaran.domain.usecase.MainUseCase
import org.koin.dsl.module

fun usecasesModule() = module {

    single<MainUseCase> {
        MainUseCase(mainItemRepository = get())
    }
}