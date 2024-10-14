package com.example.smartday.ui.components.stats

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun ProfileProgressPieChart(
    totalData: Int,
    progressData: Int,
    pieSize: Dp,
    progressColor: Color,
    backgroundColor: Color,
    pieValueColor: Color
) {
    val pieValue = (progressData.toFloat() / totalData.coerceAtLeast(1).toFloat() * 100).toInt()
        .toString() + "%"

    val progressFloatValue = (360 * progressData.toFloat()) / totalData.toFloat()
    var remainedFloatValue = 0f

    if (progressFloatValue < 360) {
        remainedFloatValue = 360 - progressFloatValue
    }

    val pieValues = listOf(
        Pair(progressFloatValue, progressColor),
        Pair(remainedFloatValue, backgroundColor)
    )

    val pieStrokeWidth = (pieSize.value * 0.1).dp

    val pieValueMeasurer = rememberTextMeasurer()
    val pieValueLayoutResult = pieValueMeasurer.measure(text = AnnotatedString(pieValue))
    val pieValueSize = pieValueLayoutResult.size

    Canvas(
        modifier = Modifier
            .size(pieSize)
            .padding(4.dp),
    ) {
        var startAngle = 270f

        pieValues.indices.forEach { i ->
            val floatData = pieValues[i].first
            val colorData = pieValues[i].second

            drawArc(
                color = colorData,
                startAngle = startAngle,
                sweepAngle = floatData,
                useCenter = false,
                style = Stroke(
                    width = pieStrokeWidth.toPx(),
                    cap = StrokeCap.Butt
                )
            )
            startAngle += floatData
        }

        drawText(
            pieValueMeasurer,
            pieValue,
            topLeft = Offset(
                (this.size.width - pieValueSize.width) / 2f,
                (this.size.height - pieValueSize.height) / 2f
            ),
            maxLines = 1,
            style = TextStyle(color = pieValueColor)
        )
    }
}


