package com.bagadesh.myallsampleapps.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by bagadesh on 02/07/22.
 */

private val borderSize = RoundedCornerShape(10.dp)

@Composable
fun MaterialColorUI() {
    if (true) {
        val listOfColors = listOf(
            "primary" to MaterialTheme.colorScheme.primary,
            "onPrimary" to MaterialTheme.colorScheme.onPrimary,
            "background" to MaterialTheme.colorScheme.background,
            "onBackground" to MaterialTheme.colorScheme.onBackground,
            "secondary" to MaterialTheme.colorScheme.secondary,
            "onSecondary" to MaterialTheme.colorScheme.onSecondary,
            "surface" to MaterialTheme.colorScheme.surface,
            "onSurface" to MaterialTheme.colorScheme.onSurface,
            "onPrimaryContainer" to MaterialTheme.colorScheme.onPrimaryContainer,
            "onSecondaryContainer" to MaterialTheme.colorScheme.onSecondaryContainer,
        )
        LazyRow {
            items(listOfColors) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                        .wrapContentSize()
                        .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = borderSize)
                        .clip(borderSize)
                        .background(it.second)
                        .padding(15.dp)
                ) {
                    ColorText(it)
                }
            }
        }
        Spacer(modifier = Modifier.size(40.dp))
    }
}

val textColors = listOf(Color.White, Color.Black)

@Composable
fun ColorText(currentPair: Pair<String, Color>) {
    Text(
        text = currentPair.first,
        fontSize = 12.sp,
        color = if (textColors.first() == currentPair.second) {
            textColors.last()
        } else {
            textColors.first()
        }
    )
}