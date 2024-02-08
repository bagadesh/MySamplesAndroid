package com.bagadesh.myallsampleapps.mainScreen.cache

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

/**
 * Created by bagadesh on 13/12/22.
 */

@Composable
fun CacheUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        val remoteDataSource = remember {
            RemoteDataSource()
        }
        val customCacheProcessor = remember {
            CustomCacheProcessor(remoteDataSource)
        }
        val cachedDataSource = remember {
            CachedDataSource(customCacheProcessor)
        }
        val scope = rememberCoroutineScope()
        var customCacheProcessorText by remember { mutableStateOf("Initialize") }
        var cachedDataSourceText by remember { mutableStateOf("Initialize") }
        var remoteDataSourceText by remember { mutableStateOf("Initialize") }
        var fetchData by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = Unit) {
            launch {
                customCacheProcessor.initialize()
                customCacheProcessorText = "Initialize finished"
            }
        }
        Text(text = "CacheProcessor - $customCacheProcessorText ", fontSize = 16.sp, modifier = Modifier.animateContentSize())
        Text(text = "CachedDataSource - $cachedDataSourceText ", fontSize = 16.sp, modifier = Modifier.animateContentSize())
        Text(text = "RemoteDataSource - $remoteDataSourceText ", fontSize = 16.sp, modifier = Modifier.animateContentSize())
        Button(onClick = {
            scope.launch {
                cachedDataSourceText = "Initialize"
                cachedDataSourceText = "finished ${cachedDataSource.getItem()}"
            }
        }) {
            Text(text = "fetchCacheData")
        }
        Button(onClick = {
            scope.launch {
                remoteDataSourceText = "Initialize"
                remoteDataSourceText = "finished ${remoteDataSource.getItem()}"
            }
        }) {
            Text(text = "fetchRemoteData")
        }
        Button(onClick = {
            scope.launch {
                remoteDataSourceText = "Initialize"
                cachedDataSourceText = "Initialize"
            }
        }) {
            Text(text = "Clear")
        }

    }
}