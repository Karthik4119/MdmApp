package com.example.mdmapplication.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NetworkViewModel(private val context: Context):ViewModel() {
    private val _networkInfo = MutableStateFlow(NetworkInfo("0 KB/s","0 KB/s","Wifi"))
    val networkInfo:StateFlow<NetworkInfo> get()= _networkInfo

    init {
        netWorkMonitoring()
    }

     fun netWorkMonitoring() {
        viewModelScope.launch {
            while (true){
                _networkInfo.value = getNetworkDetails()
                Log.d("uploadnet", _networkInfo.value.uploadSpeed.toString())
                delay(5000)
            }
        }
    }

    private fun getNetworkDetails(): NetworkInfo {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Network Capabilities of Active Network
        val nc = cm.getNetworkCapabilities(cm.activeNetwork)

        // DownSpeed in MBPS
        val downloadSpeed = (nc?.linkDownstreamBandwidthKbps)?.div(1000)?:0

        // UpSpeed  in MBPS
        val uploadSpeed = (nc?.linkUpstreamBandwidthKbps)?.div(1000)?:0

        //var networkType="Not Connected"
        val networkType=  when {
            nc?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> "Wi-Fi"
            nc?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> "Mobile Data"
            else -> "Not Connected"
        }
        return NetworkInfo(downloadSpeed.toString(),uploadSpeed.toString(),networkType)
    }
}