package com.itaran.cleanarchitecturesample.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager

class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, arg1: Intent) {
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener?.onNetworkConnectionChanged(
                isConnectedOrConnecting(arg1)
            )
        }
    }

    private fun isConnectedOrConnecting(intent: Intent): Boolean {
        val action = intent.action
        return if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            val state = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
            state?.isConnected ?: false
        } else {
            false
        }
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}