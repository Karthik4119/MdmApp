package com.example.mdmapplication.appuse

data class AppUsage(
    val appName: String,
    val send: Long,
    val received: Long,
    val duration: Long
)