package com.bagadesh.myallsampleapps.mainScreen.graphGrid

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by bagadesh on 02/03/23.
 */

val listOfHeight: List<Int> by lazy {
    listOf(2, 4, 6, 8, 10, 150, 20, 10, 5, 0)
}

@Preview
@Composable
fun GraphGridUI() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(1.dp, Color.Black)
        ) {
            drawVerticalGrids()
            drawHorizontalGrids()
            drawGraphPath(listOfHeight)
        }
    }
}

private fun DrawScope.drawGraphPath(list: List<Int>) {
    val path = generateGraphPath(list)
    drawPath(
        path = path,
        color = Color.White,
        style = Stroke(
            width = 1.dp.toPx()
        )
    )
}

private fun DrawScope.generateGraphPath(list: List<Int>): Path {
    val path = Path()
    path.moveTo(0f, size.height)
    val max = list.max()
    val yGap = (size.height / max).let { it - it * 0.1f }
    var yOffset = size.height
    var xOffset = 0f
    val xGap = size.width / list.size
    list.forEach {
        path.lineTo(xOffset, yOffset)
//        path.quadraticBezierTo(
//            x1 = xOffset,
//            y1 = ,
//            x2 = xOffset,
//            y2 = yOffset
//        )
        xOffset += xGap
        yOffset = size.height - it * yGap
    }
    return path
}

private fun DrawScope.drawHorizontalGrids() {
    val gridCount = 5
    val gap = size.height / gridCount
    var start = gap
    repeat(gridCount - 1) {
        drawLine(
            color = Color.Black,
            start = Offset(0f, start),
            end = Offset(size.width, start)
        )
        start += gap
    }
}

private fun DrawScope.drawVerticalGrids() {
    val verticalGridCount = 5
    val gap = size.width / verticalGridCount
    var start = gap
    repeat(verticalGridCount - 1) {
        drawLine(
            color = Color.Black,
            start = Offset(start, 0f),
            end = Offset(start, size.height)
        )
        start += gap
    }
}