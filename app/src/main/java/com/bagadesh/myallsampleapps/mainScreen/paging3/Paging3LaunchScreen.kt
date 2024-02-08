package com.bagadesh.myallsampleapps.mainScreen.paging3

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Created by bagadesh on 01/12/22.
 */

@Composable
fun Paging3LaunchScreen() {
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Navigating to Activity")
        }
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        delay(1000)
        val intent = Intent(context, Paging3Activity::class.java)
        context.startActivity(intent)
    })
}