package com.example.mdmapplication.appuse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppUse(viewModel: UsageViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {


    val appUseInfo = viewModel.appUsageInfo.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = "App Usage & Details")

        Card(
            elevation = 3.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(30.dp)

        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
            ) {

                Text(
                    text = appUseInfo.value.appName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )

                Text(
                    text = "PackageName",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )

            }
        }
        Card(
            elevation = 3.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(30.dp)

        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
            ) {

                Text(
                    text = getFormattedDuration(appUseInfo.value.duration),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )

                Text(
                    text = "Duration",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )

            }
        }
        Row {
            Card(
                elevation = 3.dp,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(30.dp)

            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                ) {

                    Text(
                        text = appUseInfo.value.send.toReadableSize(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )

                    Text(
                        text = "TransmittedData",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )

                }
            }
            Card(
                elevation = 3.dp,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(30.dp)

            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                ) {

                    Text(
                        text = appUseInfo.value.received.toReadableSize(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )

                    Text(
                        text = "ReceivedData",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )

                }
            }
        }


    }
}

fun getFormattedDuration(duration: Long): String {
    val seconds = (duration / 1000) % 60
    val minutes = (duration / (1000 * 60)) % 60
    val hours = (duration / (1000 * 60 * 60)) % 24

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

fun Long.toReadableSize(): String {
    return when {
        this >= 1_073_741_824 -> String.format("%.2f GB", this / 1_073_741_824.0)
        this >= 1_048_576 -> String.format("%.2f MB", this / 1_048_576.0)
        this >= 1_024 -> String.format("%.2f KB", this / 1_024.0)
        else -> "$this Bytes"
    }
}

