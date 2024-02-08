@file:OptIn(ExperimentalFoundationApi::class)

package com.bagadesh.myallsampleapps.mainScreen.collapsingtoolbar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bagadesh.myallsampleapps.R
import kotlin.math.roundToInt

/**
 * Created by bagadesh on 18/08/23.
 */

@Composable
fun CollapsingToolbar2() {
//    MyCustomColumn {
//        Header()
//        Body()
//        Tabs()
//    }
    MyCustomColumn2(
        header = {
            Header()
        },
        tabs = {Tabs()}
    ) {
        Body(modifier = Modifier.padding(it))
    }
}

enum class MyCollapse { HEADER, TABS, CONTENT }

@Composable
fun MyCustomColumn2(
    header: @Composable () -> Unit,
    tabs: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    var scrollOffsetY by remember { mutableStateOf(0f) }
    var mHeaderHeight by remember { mutableStateOf(0f) }
    val connection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                println("onPreScroll -> $available, $source")
//                if (available.y > 0f) return Offset.Zero
                val newOffset = scrollOffsetY + available.y
                scrollOffsetY = newOffset.coerceIn(-Float.MAX_VALUE, 0f)
                println("onPreScroll -> $available, $scrollOffsetY, $source")
//                if (scrollOffsetY >= (mHeaderHeight)) {
//                    return Offset.Zero
//                }
                return Offset.Zero
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                println("onPostScroll ->$consumed $available, $source")
                return super.onPostScroll(consumed, available, source)
            }
        }
    }
    SubcomposeLayout(Modifier.nestedScroll(connection)) { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        layout(layoutWidth, layoutHeight) {
            val headerPlaceables = subcompose(MyCollapse.HEADER, header).map {
                it.measure(looseConstraints)
            }
            val topBarHeight = headerPlaceables.maxByOrNull { it.height }?.height ?: 0
            val bodyContentPlaceables = subcompose(MyCollapse.CONTENT) {
                val insets = contentWindowInsets.asPaddingValues(this@SubcomposeLayout)
                val innerPadding = PaddingValues(
                    top =
                    if (headerPlaceables.isEmpty()) {
                        insets.calculateTopPadding()
                    } else {
                        topBarHeight.toDp()
                    },
                    bottom = 0.dp,
                    start = insets.calculateStartPadding((this@SubcomposeLayout).layoutDirection),
                    end = insets.calculateEndPadding((this@SubcomposeLayout).layoutDirection)
                )
                Column {
//                    Spacer(modifier = Modifier.height(topBarHeight.toDp()))
                    content(innerPadding)
                }
            }.map { it.measure(looseConstraints) }
            bodyContentPlaceables.forEach {
                it.place(0, 0)
            }
            headerPlaceables.forEach {
                it.place(0, 0)
            }
        }
    }
}

@Composable
fun MyCustomColumn(
    modifier: Modifier = Modifier,
    content: @Composable @UiComposable () -> Unit,
) {
    var scrollOffsetY by remember { mutableStateOf(0f) }
    var mHeaderHeight by remember { mutableStateOf(0f) }
    val connection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                println("onPreScroll -> $available, $source")
//                if (available.y > 0f) return Offset.Zero
                val newOffset = scrollOffsetY - available.y
                scrollOffsetY = newOffset.coerceIn(0f, Float.POSITIVE_INFINITY)
                println("onPreScroll -> $available, $scrollOffsetY, $source")
                if (scrollOffsetY >= (mHeaderHeight)) {
                    return Offset.Zero
                }
                return available
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                println("onPostScroll ->$consumed $available, $source")
                return super.onPostScroll(consumed, available, source)
            }
        }
    }
    Layout(
        content = content, modifier = modifier then Modifier.nestedScroll(connection)
    ) { measureables, constraints ->
        println("datmug -> measureablesSize = ${measureables.size}, $measureables")
        println("datmug -> constraints $constraints")
        val placeables = measureables.map {
            println("datmug -> parentData ${it.parentData}")
            it.measure(constraints)
        }
        layout(100, 100) {
            var headerStartX = 0
            var headerStartY = -scrollOffsetY.roundToInt()


            val header = placeables[0]
            val body = placeables[1]
            val tabs = placeables[2]

            header.place(headerStartX, headerStartY)


            val tabsStartX = 0
            val tabsStartY = (headerStartY + header.height - tabs.height).coerceIn(0, Int.MAX_VALUE)

            tabs.place(tabsStartX, tabsStartY)

            var bodyStartX = 0
            var bodyStartY = (tabsStartY + tabs.height).coerceIn(0, Int.MAX_VALUE)

            body.place(bodyStartX, bodyStartY)



            println("datmug, placement header height = ${header.height}")
            println("datmug, placement body height = ${body.height}, $bodyStartY")

            mHeaderHeight = header.height.toFloat()
        }
    }

}

@Composable
fun Body(modifier: Modifier = Modifier) {
//    HorizontalPager(
//        pageCount = 3,
//        modifier = modifier
//    ) {
//        LazyColumn(modifier = Modifier) {
//            items(100) {
//                var count by remember {
//                    mutableStateOf(0)
//                }
//                TextButton(onClick = { count += 1 }) {
//                    Text(
//                        text = "${list[it]} - $count",
//                        style = MaterialTheme.typography.bodyLarge,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp)
//                    )
//                }
//            }
//        }
//    }
}


@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.blue),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        var count by remember {
            mutableStateOf(0)
        }
        Button(onClick = { count += 1 }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "TextButton $count")
        }

    }
}

@Composable
fun Tabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
    ) {
        var b1count by remember {
            mutableStateOf(0)
        }
        Button(onClick = { b1count += 1 }, modifier = Modifier) {
            Text(text = "Button1 $b1count")
        }
        var b2count by remember {
            mutableStateOf(0)
        }
        Button(onClick = { b2count += 1 }, modifier = Modifier) {
            Text(text = "Button2 $b2count")
        }

        var b3count by remember {
            mutableStateOf(0)
        }
        Button(onClick = { b3count += 1 }, modifier = Modifier) {
            Text(text = "Button3 $b3count")
        }
    }
}