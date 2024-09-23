package com.example.mdmapplication

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mdmapplication.ui.theme.MdmApplicationTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.mdmapplication.appuse.UsageViewModel
import com.example.mdmapplication.battery.BatteryViewModel
import com.example.mdmapplication.network.NetworkViewModel
import com.example.mdmapplication.storage.StorageViewModel

class MainActivity : ComponentActivity() {

    private lateinit var batteryViewModel: BatteryViewModel

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        batteryViewModel = BatteryViewModel()
        val netWorkViewModel = NetworkViewModel(this)
        val storageViewModel = StorageViewModel()
        val usageViewModel = UsageViewModel(this)
        registerReceiver(batteryViewModel.batteryChangedReceiver, IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        })
        setContent {
            MdmApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        viewModel = batteryViewModel,
                        nwViewModel = netWorkViewModel,
                        storageViewModel = storageViewModel,
                        usageViewModel = usageViewModel
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryViewModel.batteryChangedReceiver)
    }
}

@Composable
fun MainScreen(
    viewModel: BatteryViewModel,
    nwViewModel: NetworkViewModel,
    storageViewModel: StorageViewModel,
    usageViewModel: UsageViewModel
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Navigation(
                navController,
                viewModel,
                nwViewModel,
                storageViewModel,
                usageViewModel
            )
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MdmApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Green) {
            MainScreen(BatteryViewModel(), applicationContext)
        }
    }
}*/
