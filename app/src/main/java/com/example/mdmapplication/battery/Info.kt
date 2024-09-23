package com.example.mdmapplication.battery


data class Data(val label: String,
                val value: String
)

enum class BatteryStatus(val label : String) {
    FULL("Full"),
    CHARGING("Charging"),
    NOT_CHARGING("Not charging"),
    DIS_CHARGING("Discharging"),
    NO_STATUS("No status")
}
enum class BatterySource(val label: String) {
    PLUGGED_AC("AC Adapter"),
    PLUGGED_USB("USB charger"),
    PLUGGED_WIRELESS("Wireless charger"),
    NO_POWER("No power")
}