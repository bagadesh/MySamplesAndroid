package com.bagadesh.myallsampleapps.mainScreen.flow

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.bagadesh.myallsampleapps.others.CountSampleUI
import com.bagadesh.myallsampleapps.others.TitleBox
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by bagadesh on 05/02/23.
 */
@Composable
fun StateFlowSample(
    duration: () -> Float
) {
    val scope = rememberCoroutineScope()

    val flow: MutableStateFlow<RefreshPage> = remember { MutableStateFlow(RefreshPage.NONE()) }
    var stateFlowResult by remember { mutableStateOf("") }

    TitleBox(title = "Result") {
        Text(text = stateFlowResult)
    }
    fun observe() {
        scope.launch {
            flow.collectLatest {
                stateFlowResult = "$it"
            }
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        observe()
    })
    Button(onClick = {
        observe()
    }) {
        Text(text = "observe")
    }
    var emitJob: Job? by remember {
        mutableStateOf(null)
    }
    Row(modifier = Modifier.fillMaxWidth().animateContentSize(), horizontalArrangement = Arrangement.SpaceAround) {
        Button(onClick = {
            emitJob = scope.launch {
                repeat(100) {
                    flow.emit(RefreshPage.Refresh("count = $it"))
                    delay(duration().toLong())
                }
            }
        }) {
            Text(text = "emit")
        }
        if (emitJob != null) {
            Button(onClick = {
                emitJob?.cancel()
                emitJob = null
            }, modifier = Modifier.animateContentSize()) {
                Text(text = "stop emitJob")
            }
        }
    }

    Button(onClick = {
        scope.launch {
            repeat(100) {
                flow.tryEmit(RefreshPage.Refresh("count = $it"))
                delay(duration().toLong())
            }
        }
    }) {
        Text(text = "tryEmit")
    }
    var count by remember { mutableStateOf(0) }
    repeat(count) {
        key(it) {
            TitleBox(
                title = "Addition $it",
                content = {
                    var text by remember {
                        mutableStateOf("")
                    }
                    LaunchedEffect(key1 = Unit, block = {
                        scope.launch {
                            flow.collectLatest { page ->
                                text = "$page"
                            }
                        }
                    })
                    Text(text = text)
                }
            )
        }
    }
    CountSampleUI(
        modifier = Modifier,
        add = {
            count++
        },
        minus = {
            count--
        },
        content = {
            Text(text = "Count = $count")
        }
    )
}
