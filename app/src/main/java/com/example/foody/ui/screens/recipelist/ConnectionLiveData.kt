package com.example.foody.ui.screens.recipelist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

enum class InternetState {
    CONNECTED,
    DISCONNECTED
}

@Suppress("DEPRECATION")
class ConnectionLiveData(
    private val context: Context
) : LiveData<InternetState>() {

    private var intentFilter = IntentFilter(CONNECTIVITY_ACTION)
    private lateinit var networkCallback: NetworkCallback
    private var connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallback = NetworkCallback(this)
        }
    }

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ->
                connectivityManager.registerDefaultNetworkCallback(
                    networkCallback
                )
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val builder =
                    NetworkRequest.Builder().addTransportType(TRANSPORT_CELLULAR).addTransportType(
                        TRANSPORT_WIFI
                    )
                connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
            }
            else -> {
                context.registerReceiver(networkReceiver, intentFilter)
            }
        }
    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val status =
            if (activeNetwork?.isConnectedOrConnecting == true) InternetState.CONNECTED else InternetState.DISCONNECTED
        postValue(status)
    }

    override fun onInactive() {
        super.onInactive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } else {
            context.unregisterReceiver(networkReceiver)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    class NetworkCallback(
        private val liveData: ConnectionLiveData
    ) : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            liveData.postValue(InternetState.CONNECTED)
        }

        override fun onLost(network: Network) {
            liveData.postValue(InternetState.DISCONNECTED)
        }
    }
}
