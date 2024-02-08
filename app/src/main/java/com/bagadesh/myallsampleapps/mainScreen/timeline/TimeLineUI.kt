package com.bagadesh.myallsampleapps.mainScreen.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * Created by bagadesh on 24/01/23.
 */

@Composable
fun TimeLineUI() {
    var finished by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        splashTracker.mark("Initial")
        delay(100)
        splashTracker.mark("First")
        delay(100)
        splashTracker.mark("Second")
        delay(100)
        splashTracker.mark("Third")
        delay(100)
        splashTracker.mark("Finish")
        delay(100)
        splashTracker.mark("Initial")
        delay(100)
        splashTracker.mark("First")
        delay(100)
        splashTracker.mark("Second")
        delay(100)
        splashTracker.mark("Third")
        delay(100)
        splashTracker.mark("Finish")
        finished = true
    }

    if (finished) {
        val result = remember {
            splashTracker.getTimeLine()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = result.mainTag,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            result.timelines.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(0.dp),
                    verticalAlignment = Alignment.Top
                ) {

                    CircleIndicator(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                    )
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = it.tag, fontSize = 20.sp, modifier = Modifier.padding(start = 0.dp)
                        )
                        Text(
                            text = "difference ${it.loggedTimeBasedOnTime} ms", fontSize = 18.sp,
                            color = Color(0xFF767B7B),
                        )
                        Text(
                            text = "${it.loggedTimeFromStart} ms",
                            fontSize = 18.sp,
                            color = Color(0xFF767B7B),
                        )
                    }
                }
            }
        }
    } else {
        Row {
            CircularProgressIndicator()
            Text(text = "Loading")
        }
    }
}

@Composable
fun CircleIndicator(
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(50)
    Column(
        modifier = modifier then Modifier
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .size(20.dp)
                .background(color = Color(0xFF379FB5))
                .border(1.dp, Color.White, shape)
                .drawWithContent {
                    drawContent()
                    drawCircle(
                        color = Color.White,
                        radius = size.minDimension / 4
                    )
                }
        )
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxHeight()
                .width(1.dp)
                .background(Color.White)
                .weight(1f)
        )
    }


}