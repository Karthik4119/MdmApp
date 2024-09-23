package com.example.mdmapplication.network

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun NetworkInfoScreen(viewModel: NetworkViewModel = viewModel()) {
    val networkInfo = viewModel.networkInfo.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Network Usage & Details")

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
                    text = "${networkInfo.value.uploadSpeed}Kbps",
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
                    text = "UploadSpeed",
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
                    text = "${networkInfo.value.downloadSpeed}Kbps",
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
                    text = "DownloadSpeed",
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
                    text = networkInfo.value.networkType,
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
                    text = "NetworkType",
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


