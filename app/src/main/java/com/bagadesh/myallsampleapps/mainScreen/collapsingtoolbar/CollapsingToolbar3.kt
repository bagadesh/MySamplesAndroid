package com.bagadesh.myallsampleapps.mainScreen.collapsingtoolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.bagadesh.myallsampleapps.R
import com.bagadesh.myallsampleapps.databinding.CollapseScreenBinding

/**
 * Created by bagadesh on 18/08/23.
 */

@Composable
fun CollapsingToolbar3() {
    AndroidViewBinding(
        factory = CollapseScreenBinding::inflate,
        modifier = Modifier.fillMaxSize()
    ) {
        composeView.setContent {
            Body(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(rememberNestedScrollInteropConnection())
            )
        }
        toolbarComposeView.setContent {
            Row(modifier = Modifier.fillMaxSize()) {
//                var count by remember { mutableStateOf(0) }
//                Button(onClick = { count += 1 }, modifier = Modifier.align(Alignment.BottomCenter)) {
//                    Text(text = "toolbarComposeView - TextButton $count")
//                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }

            }
        }

        tabsContent.setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Green))
        }
    }
}