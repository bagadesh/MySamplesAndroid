package com.bagadesh.myallsampleapps.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/**
 * Created by bagadesh on 03/10/23.
 */

@Composable
fun BottomNavigation() {
    var selected by remember {
        mutableStateOf(0)
    }
    Column {
        Spacer(modifier = Modifier.weight(1f))
        androidx.compose.material.BottomNavigation {
            remember {
                1..5
            }.forEach {
                BottomNavigationItem(
                    selected = selected == it, onClick = { selected = it },
                    icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "") })
            }
        }
    }



}