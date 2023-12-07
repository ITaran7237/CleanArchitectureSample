package com.itaran.cleanarchitecturesample.ui.screens.base

import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.itaran.cleanarchitecturesample.broadcasts.ConnectivityReceiver
import com.itaran.data.prefs.IPrefs
import org.koin.android.ext.android.get

abstract class BaseActivity<VM : BaseVM> : AppCompatActivity(),
    ConnectivityReceiver.ConnectivityReceiverListener {

    abstract val layoutRes: Int
    abstract val viewModel: VM
    private var rootView: ViewGroup? = null
    abstract fun buttonClickListener()
    abstract fun liveDataObserver()
    private var alertDialog: AlertDialog? = null
    private lateinit var receiver: ConnectivityReceiver
    private val prefs: IPrefs = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        receiver = ConnectivityReceiver()
        registerReceiver(receiver, IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION))
        rootView = window.decorView.findViewById(android.R.id.content)
        viewModel.onCreated()
        buttonClickListener()
        liveDataObserver()
        observeToError()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    fun startLoadingDialog() {}

    fun stopLoadingDialog() {}

    private fun observeToError() {
        viewModel.publicExceptionHandlerLiveData.observe(this, Observer {
            stopLoadingDialog()
        })
    }

    protected fun showServerError() {}

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        prefs.setWifiConnect(isConnected)
    }
}