package com.itaran.cleanarchitecturesample.ui.screens.main

import com.itaran.domain.usecase.MainUseCase
import kotlinx.coroutines.launch

class MainViewModelImpl(private val mainUseCase: MainUseCase) : MainViewModel() {

    override fun getUser() {
        launch {}
    }
}