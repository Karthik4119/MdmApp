package com.example.mdmapplication.storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mdmapplication.appuse.toReadableSize
import kotlinx.coroutines.delay


@Composable
fun StorageScreen(viewModel: StorageViewModel = viewModel()) {


    val storageInfo = viewModel.storageInfo.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Storage Usage & Details")

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

                    text = storageInfo.value.totalSpace.toReadableSize(),
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
                    text = "Total Space",
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
                    text = storageInfo.value.availableSpace.toReadableSize(),
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
                    text = "Available Space",
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
                    text = storageInfo.value.usedSpace.toReadableSize(),
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
                    text = "Used Space",
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
