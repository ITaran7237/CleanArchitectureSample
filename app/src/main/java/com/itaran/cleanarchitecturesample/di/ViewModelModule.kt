package com.itaran.cleanarchitecturesample.di
import com.itaran.cleanarchitecturesample.ui.screens.main.MainViewModel
import com.itaran.cleanarchitecturesample.ui.screens.main.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {

    viewModel<MainViewModel> {
        MainViewModelImpl(mainUseCase = get())
    }
}