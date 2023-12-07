package com.itaran.cleanarchitecturesample.ui.screens.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap


abstract class BaseVM : ViewModel(), BaseViewModel, CoroutineScope{

    val publicExceptionHandlerLiveData = MutableLiveData<Throwable>()
    override val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val indicatorLoaderScopeMap = ConcurrentHashMap<Any, CoroutineScope>()

    override val coroutineContext =
        SupervisorJob() + Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            baseExceptionHandler(throwable)
        }

    private fun baseExceptionHandler(throwable: Throwable) {
        Log.e("Error", throwable.message, throwable)
        publicExceptionHandlerLiveData.postValue(throwable)
    }

    @CallSuper
    open fun onCreated() {
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    protected fun CoroutineScope.launchWithLoader(
        indicatorLiveData: MutableLiveData<Boolean>,
        block: suspend CoroutineScope.() -> Unit
    ): Job = run {
        fun createNewLoaderJob() = launch(start = CoroutineStart.LAZY) {
            indicatorLiveData.postValue(true)
        }.apply {
            invokeOnCompletion {
                indicatorLiveData.postValue(false)
                indicatorLoaderScopeMap.remove(indicatorLiveData)
            }
        }

        val loaderScope = indicatorLoaderScopeMap[indicatorLiveData]
            ?.takeIf { it.isActive }
            ?: (this + createNewLoaderJob())
                .also {
                    indicatorLoaderScopeMap[indicatorLiveData] = it
                }
        loaderScope.launch {
            block()
        }
    }
}
