package com.example.mdmapplication.battery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BatteryScreen(viewModel: BatteryViewModel) {
    /*Column(modifier = Modifier.padding(top = 35.dp).fillMaxSize()) {

        InfoCardsList(list = viewModel.dataList)
    }*/
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .padding(top = 35.dp)
                .fillMaxWidth()
        ) {
            BatteryCirleProgress(
                isCharging = viewModel.isCharging.value,
                percentage = viewModel.batteryLevel.value,
                fillColor = MaterialTheme.colors.primary,
                backgroundColor = Color.LightGray,
                strokeWidth = 15.dp
            )
        }
        InfoCardsList(list = viewModel.dataList)
    }
}
@Composable
fun InfoCardsList(list: List<Data>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp),
    ) {
        itemsIndexed(items = list) { _, item ->
            InfoItem(item)
        }
    }
}

@Composable
fun InfoItem(data: Data) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxSize()
        ) {

            Text(
                text = data.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = data.label,
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }
    }

}

@Preview
@Composable
fun PreviewBatteryScreen() {
    BatteryScreen(viewModel = BatteryViewModel())
}