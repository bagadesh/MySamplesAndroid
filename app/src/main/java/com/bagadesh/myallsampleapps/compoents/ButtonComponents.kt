package com.bagadesh.myallsampleapps.compoents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by bagadesh on 27/12/23.
 */


@Composable
fun CustomButton(text: String, onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,

            )
    ) {
        Text(text = text, color = Color.White)
    }


}