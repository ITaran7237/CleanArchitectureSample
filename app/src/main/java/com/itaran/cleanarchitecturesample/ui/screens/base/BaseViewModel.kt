package com.itaran.cleanarchitecturesample.ui.screens.base

import androidx.lifecycle.LiveData

interface BaseViewModel {

    val isLoading: LiveData<Boolean>
}