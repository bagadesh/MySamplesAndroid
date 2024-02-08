package com.bagadesh.myallsampleapps.mainScreen.lazyVerticalGrid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by bagadesh on 01/02/23.
 */

@Composable
fun LazyVerticalGridUI() {

    val list = remember {
        mutableListOf<String>().apply {
            repeat(2) {
                add("Item $it")
            }
        }
    }

    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
        columns = GridCells.Adaptive(80.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(list) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .requiredHeight(100.dp)
                    .widthIn(max = 800.dp)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier
                    .width(80.dp)
                    .height(90.dp)
                    .background(Color.Green))
            }
        }
    }
}