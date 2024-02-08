@file:OptIn(ExperimentalComposeUiApi::class)

package com.bagadesh.myallsampleapps.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bagadesh.exposed.components.ShowColors
import com.bagadesh.featureflags.FeatureFlagScreen
import com.bagadesh.myallsampleapps.features.Features
import com.bagadesh.myallsampleapps.features.ui.ExpandChangeEnvScreen
import com.bagadesh.myallsampleapps.mainScreen.autofill.AutoFillScreenUi
import com.bagadesh.myallsampleapps.mainScreen.cache.CacheUI
import com.bagadesh.myallsampleapps.mainScreen.channels.ChannelsUi
import com.bagadesh.myallsampleapps.mainScreen.collapsingtoolbar.CollapsingToolbar
import com.bagadesh.myallsampleapps.mainScreen.composeInternals.ComposeInternals
import com.bagadesh.myallsampleapps.mainScreen.coroutineException.CoroutineExceptionUI
import com.bagadesh.myallsampleapps.mainScreen.countertimerwithsync.CounterTimerSyncUi
import com.bagadesh.myallsampleapps.mainScreen.epg.EpgScreen
import com.bagadesh.myallsampleapps.mainScreen.flow.FlowExamples
import com.bagadesh.myallsampleapps.mainScreen.graphGrid.GraphGridUI
import com.bagadesh.myallsampleapps.mainScreen.home.HomeScreenCardStyle
import com.bagadesh.myallsampleapps.mainScreen.lazyVerticalGrid.LazyVerticalGridUI
import com.bagadesh.myallsampleapps.mainScreen.lazylazy.LazyLazyColumn
import com.bagadesh.myallsampleapps.mainScreen.navControllerFragment.NavControllerFragmentScreen
import com.bagadesh.myallsampleapps.mainScreen.paging3.Paging3LaunchScreen
import com.bagadesh.myallsampleapps.mainScreen.rxJava.RxJavaSampleUI
import com.bagadesh.myallsampleapps.mainScreen.rxJavaFlow.RxJavaAndFlowUI
import com.bagadesh.myallsampleapps.mainScreen.swipeToShow.SwipeToShowScreen
import com.bagadesh.myallsampleapps.mainScreen.timeline.TimeLineUI
import java.nio.channels.Channels

/**
 * Created by bagadesh on 19/11/22.
 */

val features: List<Features> by lazy {
    Features.values().toList().filter { it != Features.HOME }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var lastToFirst by remember { mutableStateOf(false) }
    val listOfFeatures = remember(lastToFirst) {
        if (lastToFirst) {
            Features.values().toList().filter { it != Features.HOME }.reversed()
        } else {
            Features.values().toList().filter { it != Features.HOME }
        }
    }
    Scaffold {
        LaunchedEffect(key1 = Unit, block = {
            if (Features.default != Features.HOME) {
                navController.navigate(route = Features.default.name)
            }
        })
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            NavHost(
                modifier = Modifier
                    .fillMaxSize(),
                navController = navController,
                startDestination = Features.HOME.name
            ) {
                composable(route = Features.HOME.name) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        MaterialTheme.ShowColors()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "Last to first ")
                            Checkbox(checked = lastToFirst, onCheckedChange = { lastToFirst = !lastToFirst })
                        }
                        HomeScreenCardStyle(result = listOfFeatures) {
                            navController.navigate(route = it.name)
                        }
                    }
                }
                composable(route = Features.EXPAND_CHANGE_ENVIRONMENT.name) {
                    ExpandChangeEnvScreen()
                }
                composable(route = Features.RANDOM_CANVAS.name) {
//                    RandomCanvas()
                }
                composable(route = Features.NAV_CONTROLLER_FRAGMENT_TRANSACTION.name) {
                    NavControllerFragmentScreen()
                }
                composable(route = Features.PAGING3.name) {
                    Paging3LaunchScreen()
                }
                composable(route = Features.CACHE_UI.name) {
                    CacheUI()
                }
                composable(route = Features.COMPOSE_INTERNALS.name) {
                    ComposeInternals()
                }
                composable(route = Features.FLOW_EXAMPLES.name) {
                    FlowExamples()
                }
                composable(route = Features.TIME_LINE_MARKER.name) {
                    TimeLineUI()
                }
                composable(route = Features.COROUTINE_EXCEPTION_HANDLER.name) {
                    CoroutineExceptionUI()
                }
                composable(route = Features.LazyVerticalGridUI.name) {
                    LazyVerticalGridUI()
                }
                composable(route = Features.RxJavaSampleUI.name) {
                    RxJavaSampleUI()
                }
                composable(route = Features.RxJavaAndFlowUI.name) {
                    RxJavaAndFlowUI()
                }
                composable(route = Features.GraphGrid.name) {
                    GraphGridUI()
                }
                composable(route = Features.SwipeToShow.name) {
                    SwipeToShowScreen()
                }
                composable(route = Features.FeatureFlagUI.name) {
                    FeatureFlagScreen()
                }
                composable(route = Features.CounterTimerSyncUi.name) {
                    CounterTimerSyncUi()
                }
                composable(route = Features.AutoFillExample.name) {
                    AutoFillScreenUi()
                }
                composable(route = Features.CollapsingToolbar.name) {
                    CollapsingToolbar()
                }
                composable(route = Features.BottomNavigation.name) {
                    BottomNavigation()
                }
                composable(route = Features.Epg.name) {
                    EpgScreen()
                }
                composable(route = Features.KotlinChannels.name) {
                    ChannelsUi()
                }
                composable(route = Features.LazyLazyColumn.name) {
                    LazyLazyColumn()
                }
            }
        }
    }
}
