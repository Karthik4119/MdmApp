package com.example.mdmapplication.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BatteryViewModel : ViewModel() {

    val dataList = mutableStateListOf<Data>()
    val batteryLevel = mutableStateOf(-1)
    val isCharging = mutableStateOf(false)

    val batteryChangedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            onActionBatteryChanged(intent)
        }
    }

    fun onActionBatteryChanged(intent: Intent) {
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0).toDouble() / 1000
        val technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
        val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10


        val source = when (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
            BatteryManager.BATTERY_PLUGGED_AC -> BatterySource.PLUGGED_AC
            BatteryManager.BATTERY_PLUGGED_USB -> BatterySource.PLUGGED_USB
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> BatterySource.PLUGGED_WIRELESS
            else -> BatterySource.NO_POWER
        }

        val status = when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
            BatteryManager.BATTERY_STATUS_FULL -> BatteryStatus.FULL
            BatteryManager.BATTERY_STATUS_CHARGING -> BatteryStatus.CHARGING
            BatteryManager.BATTERY_STATUS_DISCHARGING -> BatteryStatus.DIS_CHARGING
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> BatteryStatus.NOT_CHARGING
            else -> BatteryStatus.NO_STATUS
        }
        isCharging.value = status == BatteryStatus.CHARGING
        batteryLevel.value = level
        dataList.clear()
        dataList.addAll(
            listOf(
                Data("Percentage",  "${batteryLevel.value} %"),
                Data("Temperature", "${temperature}°C"),
                Data("Source", source.label),
                Data("Status", status.label),
                Data("Technologie", technology.toString()),
                Data("Voltage", "${voltage}V"),
            )
        )
    }
}