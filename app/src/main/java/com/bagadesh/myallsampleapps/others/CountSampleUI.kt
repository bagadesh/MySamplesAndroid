package com.bagadesh.myallsampleapps.others

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bagadesh.myallsampleapps.R

/**
 * Created by bagadesh on 04/02/23.
 */


@Composable
fun CountSampleUI(
    modifier: Modifier = Modifier,
    minus: () -> Unit,
    add: () -> Unit,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier then Modifier
            .border(1.dp, Color.White, RectangleShape)
            .fillMaxWidth()
            .padding(10.dp)
        , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = minus) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_minimize_24),
                contentDescription = ""
            )
        }
        Button(onClick = add) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = ""
            )
        }
        content()
    }
}