package com.example.mdmapplication.appuse

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsageViewModel(private val context: Context) : ViewModel() {


    private val _appUsageInfo = MutableStateFlow(AppUsage("",0L,0L,0L))
    val appUsageInfo: StateFlow<AppUsage> get() = _appUsageInfo

    init {
        appUseMonitoring()
    }

    private fun appUseMonitoring() {
        viewModelScope.launch {
            while (true){
                _appUsageInfo.value = getTotalDataUsage(context)
                delay(5000)
            }
        }
    }

    private fun getTotalDataUsage(context: Context): AppUsage {
        val networkStatsManager = context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

        val networkStats = networkStatsManager.querySummary(
            ConnectivityManager.TYPE_WIFI,
            "",
            0,
            System.currentTimeMillis()
        )



        var totalReceivedBytes: Long = 0
        var totalTransmittedBytes: Long = 0
        var packageName = ""
        var duration:Long=0
        val bucket = NetworkStats.Bucket()
        val packageManager = context.packageManager
        val installedPackages = packageManager.getInstalledPackages(0).associateBy { it.applicationInfo.uid }
        while (networkStats?.hasNextBucket() == true) {
            networkStats.getNextBucket(bucket)  // Populate the bucket with the next bucket's data
            totalReceivedBytes += bucket.getRxBytes()  // Get received bytes
            totalTransmittedBytes += bucket.getTxBytes()  // Get transmitted bytes
            val startTime = bucket.getStartTimeStamp()
            val endTime = bucket.getEndTimeStamp()
            duration = endTime - startTime // Duration in milliseconds
            val uid =bucket.uid
            val packageInfo = installedPackages[uid]

            if (packageInfo != null) {
                packageName = packageInfo.packageName
            } else {
                Log.e("DataUsage", "Package not found for UID: $uid")
            }
        }
        return AppUsage(packageName,totalTransmittedBytes,totalReceivedBytes,duration)
    }
}