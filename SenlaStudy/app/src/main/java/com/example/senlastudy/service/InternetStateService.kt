package com.example.senlastudy.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Binder
import android.os.Build
import android.os.IBinder


class InternetStateService : Service(), Observable {

    override val observers: ArrayList<Observer> = ArrayList()
    private val serviceBinder = ServiceBinder()

    private var isConnected = false
        set(value) {
            field = value
            sendUpdateEvent()
        }

    private val receiverStateInternet = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            isConnected = isConnectedToInternet(context)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return serviceBinder
    }

    inner class ServiceBinder : Binder() {
        fun getService(): InternetStateService {
            return this@InternetStateService
        }
    }

    override fun onCreate() {
        super.onCreate()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiverStateInternet, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiverStateInternet)
    }

    private fun isConnectedToInternet(context: Context?): Boolean {
        var result = false
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }
        return result
    }
}