package com.itaran.cleanarchitecturesample.ui.screens.main

import com.itaran.cleanarchitecturesample.ui.screens.base.BaseVM

abstract class MainViewModel : BaseVM() {

    abstract fun getUser()
}