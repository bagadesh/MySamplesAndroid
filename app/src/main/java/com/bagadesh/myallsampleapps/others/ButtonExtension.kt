package com.bagadesh.myallsampleapps.others

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*

/**
 * Created by bagadesh on 08/02/23.
 */

@Composable
fun ActionButton(
    title: String,
    action: () -> Unit,
    content: @Composable (String) -> Unit
) {
    var state by remember { mutableStateOf(false) }
    AnimatedVisibility(visible = !state) {
        Button(onClick = {
            action()
            state = true
        }) {
            Text(text = title)
        }
    }
    AnimatedVisibility(visible = state) {
        content(title)
    }
}