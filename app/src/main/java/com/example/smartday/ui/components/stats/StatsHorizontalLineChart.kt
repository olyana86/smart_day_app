package com.example.smartday.ui.components.stats

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun StatsHorizontalLineChart(
    modifier: Modifier,
    data: List<Int>,
    colorOfSuccess: Color,
    colorOfFailure: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        for (dataList in data.chunked(100)) {
            Box {
                Canvas(modifier = modifier.fillMaxWidth()) {
                    val height = 50f
                    dataList.indices.forEach { i ->
                        val info = data[i]
                        val sizeWidth = size.width / dataList.size
                        val x1 = i * sizeWidth
                        var barColor = colorOfSuccess

                        if (info == 0) {
                            barColor = colorOfFailure
                        }
                        drawRect(
                            color = barColor,
                            topLeft = Offset(x1, 50f),
                            size = Size(width = sizeWidth, height = height)
                        )
                    }
                }
            }
        }
    }
}