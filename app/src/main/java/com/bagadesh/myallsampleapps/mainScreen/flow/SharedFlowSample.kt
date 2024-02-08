package com.bagadesh.myallsampleapps.mainScreen.flow

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.bagadesh.myallsampleapps.others.CountSampleUI
import com.bagadesh.myallsampleapps.others.TitleBox
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by bagadesh on 05/02/23.
 */

@Composable
fun SharedFlowSample(
    duration: () -> Float
) {
    var replayCache by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val flow: MutableSharedFlow<RefreshPage> = remember(replayCache) { MutableSharedFlow(replay = replayCache) }
    var flowResult by remember { mutableStateOf("") }

    fun observe() {
        scope.launch {
            flow.collectLatest {
                flowResult = "$it"
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        observe()
    })


    CountSampleUI(minus = { replayCache-- }, add = { replayCache++ }) {
        Text(text = "Replay Cache $replayCache")
    }

    Button(onClick = {
        observe()
    }) {
        Text(text = "observe")
    }

    Button(onClick = { /*TODO*/ }) {
        Column {
            Text(text = "Result box")
            Text(text = flowResult)
        }
    }
    Button(onClick = {
        scope.launch {
            repeat(100) {
                flow.emit(RefreshPage.Refresh("count = $it"))
                delay(duration().toLong())
            }
        }
    }) {
        Text(text = "scope.launch -> emit")
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
