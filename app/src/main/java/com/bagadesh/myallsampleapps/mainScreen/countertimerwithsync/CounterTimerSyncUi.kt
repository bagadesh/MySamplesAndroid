package com.bagadesh.myallsampleapps.mainScreen.countertimerwithsync

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toIntRect
import androidx.hilt.navigation.compose.hiltViewModel
import com.bagadesh.myallsampleapps.mainScreen.countertimerwithsync.Location.*
import kotlin.math.roundToInt

/**
 * Created by bagadesh on 01/06/23.
 */

@Composable
fun CounterTimerSyncUi(
    vm: CounterTimerSyncViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        var location by remember { mutableStateOf(CENTER) }

        var initialXPositionOfButton by remember { mutableStateOf(0f) }
        var initialYPositionOfButton by remember { mutableStateOf(0f) }

        val x by remember {
            derivedStateOf {
                when (location) {
                    LEFT_TOP -> -initialXPositionOfButton
                    RIGHT_TOP -> 0f
                    RIGHT_BOTTOM -> 0f
                    LEFT_BOTTOM -> 0f
                    CENTER -> 0f
                }
            }
        }
        val y by remember {
            derivedStateOf {
                when (location) {
                    LEFT_TOP -> -initialYPositionOfButton
                    RIGHT_TOP -> 0f
                    RIGHT_BOTTOM -> 0f
                    LEFT_BOTTOM -> 0f
                    CENTER -> 0f
                }
            }
        }

        val animX by animateFloatAsState(targetValue = x, label = "X")
        val animY by animateFloatAsState(targetValue = y, label = "Y")

        Button(
            onClick = {
                location = location.next()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .onGloballyPositioned {
                    initialXPositionOfButton = it.positionInRoot().x
                    initialYPositionOfButton = it.positionInRoot().x
                }
                .offset {
                    IntOffset(animX.roundToInt(), animY.roundToInt())
                }
                .width(200.dp)
                .height(80.dp)
        ) {
            Text(text = "Button")
        }

        ButtonCommon {
            Button(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
            ) {
                Text(text = "Button")
            }
        }

    }
}

@Composable
fun ButtonCommon(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        layout(0, 0) {

        }
    }
}


enum class Location {
    LEFT_TOP, RIGHT_TOP, RIGHT_BOTTOM, LEFT_BOTTOM, CENTER,
}

fun Location.next(): Location {
    val size = values().size
    val index = if (ordinal + 1 >= size) 0 else ordinal + 1
    return values()[index]
}