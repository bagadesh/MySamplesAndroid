package com.bagadesh.myallsampleapps.others

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by bagadesh on 05/02/23.
 */

@Composable
fun RowSelectionSample(
    selected: String,
    listOfItems: List<String>,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        listOfItems.forEach {
            key(it) {
                TextButton(
                    onClick = {
                        onItemSelected(it)
                    },
                    modifier = if (selected == it) Modifier.border(width = 1.dp, Color.White, RoundedCornerShape(10.dp)) else Modifier
                ) {
                    Text(text = it)
                }
            }
        }
    }
}