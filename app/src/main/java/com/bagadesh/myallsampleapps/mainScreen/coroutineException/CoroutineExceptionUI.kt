package com.bagadesh.myallsampleapps.mainScreen.coroutineException

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.RuntimeException

/**
 * Created by bagadesh on 25/01/23.
 */

@Composable
fun CoroutineExceptionUI() {
    var text by remember {
        mutableStateOf("Executing")
    }
    val specialHandler = remember {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            text = "SpecialHandler \n"
            text += "Exception Message: " + throwable.message
        }
    }
    val scope = remember {
        val job = SupervisorJob()
        val dispatcher = Dispatchers.IO
        val commonHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            text = "CommonHandler \n"
            text += "Exception Message: " + throwable.message
        }
        val scope = CoroutineScope(dispatcher + job + commonHandler)
        scope
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(10.dp)
            ) {
                Button(modifier = Modifier.padding(10.dp), onClick = {
                    scope.launch {
                        throw RuntimeException("RuntimeException")
                    }
                }) {
                    Text(text = "Normal Launch")
                }

                Button(modifier = Modifier.padding(10.dp), onClick = {
                    scope.launch {
                        coroutineScope {
                            launch(Dispatchers.IO + specialHandler) {
                                throw RuntimeException("RuntimeException+Dispatchers.IO")
                            }
                        }
                        delay(1000)
                    }
                }) {
                    Text(text = "Launch inside CoroutineScope + specialHandler")
                }

                Button(modifier = Modifier.padding(10.dp), onClick = {
                    scope.launch {
                        coroutineScope {
                            launch(Dispatchers.IO + specialHandler + SupervisorJob()) {
                                throw RuntimeException("RuntimeException+Dispatchers.IO+SupervisorJob")
                            }
                        }
                        delay(1000)
                    }
                }) {
                    Text(text = "Launch inside CoroutineScope + specialHandler + SupervisorJob")
                }
            }
            Column(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {
                    scope.launch(specialHandler) {
                        throw RuntimeException("RuntimeException")
                    }
                }) {
                    Text(text = "Normal Launch with CoroutineExceptionHandler")
                }
            }

        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = text, fontSize = 16.sp, modifier = Modifier
                    .padding(10.dp)
                    .animateContentSize()
            )
        }
    }


}