package com.bagadesh.myallsampleapps.others

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by bagadesh on 04/02/23.
 */

@Preview
@Composable
fun TitleBoxPreview() {
    TitleBox(
        modifier = Modifier,
        title = "Title",
        content = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "SampleButton")
            }
        }
    )
}

@Composable
fun TitleBox(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier then Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .wrapContentHeight()
            .border(1.dp, Color.White, RectangleShape)
    ) {
        Column {
            Box(modifier = Modifier.border(1.dp, Color.White, RectangleShape)) {
                Text(
                    text = title, modifier = Modifier.padding(10.dp), fontSize = 14.sp
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}