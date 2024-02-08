package com.bagadesh.myallsampleapps.mainScreen.swipeToShow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bagadesh.exposed.components.SwipeAlignment
import com.bagadesh.exposed.components.SwipeToAction
import com.bagadesh.exposed.components.SwipeToSlide

/**
 * Created by bagadesh on 05/04/23.
 */

@Composable
fun SwipeToShowScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center
    ) {
        SwipeToSlide(
            modifier = Modifier,
            swipeAlignment = SwipeAlignment.START,
            drag = 10,
            reveal = {
                ActionsToShow()
            }
        ) {
            DraggingCard()
        }
    }
}

@Composable
fun ActionsToShow() {
    val icons = remember { listOf(Icons.Default.Settings, Icons.Default.AccountCircle, Icons.Default.Delete) }
    Row(
        modifier = Modifier
            .background(Color.White)
            .wrapContentWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEach {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = it,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Text(text = "Testing")
    }
}

@Composable
fun DraggingCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Account", color = Color.Black)
    }
}



