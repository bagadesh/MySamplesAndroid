package com.bagadesh.myallsampleapps.mainScreen.epg

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import eu.wewox.programguide.ProgramGuide
import eu.wewox.programguide.ProgramGuideItem

/**
 * Created by bagadesh on 13/12/23.
 */

@Composable
fun EpgScreen() {
//    LazyColumnAndLazyRowExample()
    ProgramGrid()
}

@Composable
fun ProgramGrid() {
    val channels = 20
    val timeline = 8..22
    val programs = remember { createPrograms(channels, timeline) }

    ProgramGuide(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        guideStartHour = timeline.first.toFloat()

        programs(
            items = programs,
            layoutInfo = {
                ProgramGuideItem.Program(
                    channelIndex = it.channel,
                    startHour = it.start,
                    endHour = it.end,
                )
            },
            itemContent = { ProgramCell(it) },
        )

        channels(
            count = channels,
            layoutInfo = {
                ProgramGuideItem.Channel(
                    index = it
                )
            },
            itemContent = { ChannelCell(it) },
        )

        timeline(
            count = timeline.count(),
            layoutInfo = {
                val start = timeline.toList()[it].toFloat()
                ProgramGuideItem.Timeline(
                    startHour = start,
                    endHour = start + 1f
                )
            },
            itemContent = { TimelineItemCell(timeline.toList()[it].toFloat()) },
        )
    }
}
//
//@Composable
//fun LazyColumnAndLazyRowExample(items: List<Station> = createDummyData()) {
//    var offset by remember { mutableStateOf(0f) }
//    val lazyListState = rememberLazyListState()
//
//    LaunchedEffect(offset) {
//        lazyListState.dispatchRawDelta(-offset)
//    }
//
//    val nestedScrollConnection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                offset = available.x
//                return available
//            }
//        }
//    }
//
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(items = items) { item ->
//            LazyRow(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .nestedScroll(nestedScrollConnection), state = lazyListState
//            ) {
//                items(item.programs) { program ->
//                    Box(
//                        modifier = Modifier
//                            .width(100.dp)
//                            .height(50.dp)
//                    ) {
//                        Text(
//                            text = program.text,
//                            modifier = Modifier
//                                .padding(4.dp)
//                                .border(2.dp, Color.Blue)
//                                .padding(4.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//fun createDummyData(): List<Station> {
//
//    return buildList {
//        repeat(30) {
//            add(
//                Station(
//                    programs = buildList {
//                        repeat(30) {
//                            add(Program("Program $it"))
//                        }
//                    }
//                )
//            )
//        }
//    }
//}
//
//data class Station(val programs: List<Program>)
//data class Program(val text: String)