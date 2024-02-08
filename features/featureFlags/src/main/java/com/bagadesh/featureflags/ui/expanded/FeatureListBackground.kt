package com.bagadesh.featureflags.ui.expanded

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by bagadesh on 15/04/23.
 */


@Composable
fun FeatureListBackground(content: LazyListScope.() -> Unit) {
    val focusManager = LocalFocusManager.current
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            focusManager.clearFocus()
        }) {
        item {
            Text(
                text = "Feature variables\n",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        content()
    }
}