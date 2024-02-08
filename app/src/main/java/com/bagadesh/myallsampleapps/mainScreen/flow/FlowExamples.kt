package com.bagadesh.myallsampleapps.mainScreen.flow

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bagadesh.myallsampleapps.others.CountSampleUI
import com.bagadesh.myallsampleapps.others.RowSelectionSample
import com.bagadesh.myallsampleapps.others.TimeSlider
import com.bagadesh.myallsampleapps.others.TitleBox
import com.jakewharton.rxrelay3.BehaviorRelay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx3.asFlow

/**
 * Created by bagadesh on 10/01/23.
 */

sealed class RefreshPage {

    data class Refresh(val message: String = "") : RefreshPage()
    data class NONE(val unit: Unit = Unit) : RefreshPage()

}

@Composable
fun FlowExamples() {
    val navController = rememberNavController()
    var duration by remember {
        mutableStateOf(1000f)
    }
    var text by remember {
        mutableStateOf("")
    }
    val bookmarksRelay: BehaviorRelay<Int> = remember {
        BehaviorRelay.createDefault(10)
    }

    val flow = remember {
        MutableStateFlow(1)
    }

    LaunchedEffect(key1 = Unit, block = {
        flow.combine(bookmarksRelay.asFlow()) { a1, a2 ->
            text += " $a1, $a2  \n"
        }.collectLatest {
            println("$it")
        }
    })



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "ZIP result = $text") // 1,10

        Button(onClick = {
            bookmarksRelay.accept(bookmarksRelay.value!! + 1)
        }) {
            Text(text = "Update")
        }

        val scope = rememberCoroutineScope()
        Button(onClick = {
            scope.launch {
                flow.emit(flow.value + 1)
            }
        }) {
            Text(text = "Update 2")
        }

        TitleBox(title = "Duration") {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "duration is $duration")
                TimeSlider(initialValue = 1000f,
                    valueRange = 1000f..10000f,
                    onValueChange = {
                        duration = it
                    })
            }

        }

        var navEntry by remember {
            mutableStateOf("")
        }
        RowSelectionSample(
            selected = navEntry,
            listOfItems = listOf("StateFlowSample", "SharedFlowSample"),
            onItemSelected = {
                navController.navigate(route = it)
            }
        )
        LaunchedEffect(key1 = Unit, block = {
            navController.currentBackStackEntryFlow.collectLatest {
                navEntry = it.destination.route.toString()
            }
        })
        NavHost(navController = navController, startDestination = "StateFlowSample") {
            composable("StateFlowSample") {
                TitleBox(title = "StateFlowSample") {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        StateFlowSample(
                            duration = { duration }
                        )
                    }
                }
            }
            composable("SharedFlowSample") {
                TitleBox(title = "SharedFlowSample") {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SharedFlowSample { duration }
                    }
                }
            }
        }
    }
}



