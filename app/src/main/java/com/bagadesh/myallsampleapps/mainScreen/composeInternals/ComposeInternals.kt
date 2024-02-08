package com.bagadesh.myallsampleapps.mainScreen.composeInternals

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by bagadesh on 02/01/23.
 */

@Preview
@Composable
fun ComposeInternals() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        val boxModifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(1.dp, Color.Red)
        Text(text = "BoxWith Constraints", fontSize = 20.sp)
        BoxWithConstraints(modifier = boxModifier) {
            Column {
                this@BoxWithConstraints.Print()
            }
        }
        Text(text = "Box", fontSize = 20.sp)
        Box(modifier = boxModifier) {
            Text(text = "Nothing")
        }
    }
}


@Composable
fun BoxWithConstraintsScope.Print() {
    Text(text = "minWidth = $minWidth")
    Text(text = "maxWidth = $maxWidth")
    Text(text = "minHeight = $minHeight")
    Text(text = "maxHeight = $maxHeight")
}