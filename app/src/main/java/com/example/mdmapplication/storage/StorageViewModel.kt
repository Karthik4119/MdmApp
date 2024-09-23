package com.example.mdmapplication.storage

import android.os.Environment
import android.os.StatFs
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StorageViewModel: ViewModel() {
    private val _storageInfo = MutableStateFlow(StorageInfo(0L,0L,0L))
    val storageInfo: StateFlow<StorageInfo> get() = _storageInfo

    init {
        storageMonitoring()
    }

     private fun storageMonitoring() {
        viewModelScope.launch {
            while (true){
                _storageInfo.value = getExternalStorageInfo()
                Log.d("upload", _storageInfo.value.totalSpace.toString())
                delay(5000)
            }
        }
    }

    fun getExternalStorageInfo():StorageInfo {
        val stat = StatFs(Environment.getDataDirectory().path)
        val totalSpace = stat.blockCountLong * stat.blockSizeLong
        val availableSpace = stat.availableBlocksLong * stat.blockSizeLong
        val usedSpace = totalSpace - availableSpace

        return StorageInfo(totalSpace, availableSpace, usedSpace)
    }
}