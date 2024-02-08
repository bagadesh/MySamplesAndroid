package com.bagadesh.myallsampleapps.others

import androidx.compose.material3.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by bagadesh on 05/02/23.
 */

@Preview
@Composable
fun TimeSliderPreview() {
    TimeSlider(
        initialValue = 0f,
        onValueChange = {

        },
        valueRange = 1000f..10000f
    )
}

@Composable
fun TimeSlider(
    initialValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit
) {
    var value by remember {
        mutableStateOf(initialValue)
    }

    Slider(
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        valueRange = valueRange
    )
}