@file:OptIn(ExperimentalMaterial3Api::class)

package com.bagadesh.myallsampleapps.mainScreen.collapsingtoolbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.bagadesh.myallsampleapps.R

/**
 * Created by bagadesh on 18/08/23.
 */

val list = buildList<Int> {
    repeat(100) {
        add(it + 1)
    }
}

fun Dp.toPx(density: Density): Float {
    return density.run { roundToPx().toFloat() }
}

private val toolbarHeight = 56.dp

private val paddingMedium = 16.dp

private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp

private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f


@Composable
fun CollapsingToolbar() {
    CollapsingToolbar3()
//    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            LargeTopAppBar(
//                scrollBehavior = scrollBehavior,
//                title = {
//                    Text(
//                        "Centered TopAppBar",
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = { /* doSomething() */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { /* doSomething() */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Favorite,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                }
//            )
//        },
//        content = { innerPadding ->
//            LazyColumn(
//                contentPadding = innerPadding,
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                val list = (0..75).map { it.toString() }
//                items(count = list.size) {
//                    Text(
//                        text = list[it],
//                        style = MaterialTheme.typography.bodyLarge,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp)
//                    )
//                }
//            }
//        }
//    )

//    val density = LocalDensity.current
//    val scroll = rememberLazyListState(0)
//    var headerHeight by remember { mutableStateOf(0.dp) }
//    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
//    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }
//    var offsetY by remember { mutableStateOf(0f) }
//    val connection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                val newOffset = offsetY - available.y
//                offsetY = newOffset.coerceIn(0f, Float.POSITIVE_INFINITY)
//                println("datmug-1, ${offsetY}, $available")
//                return Offset.Zero
//            }
//        }
//    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .nestedScroll(connection)
//    ) {
//        Body({ offsetY }, headerHeight)
//        Header({ offsetY }, headerHeight) {
//            with(density) {
//                headerHeight = it.height.toDp()
//            }
//        }
//        Toolbar(
//            Modifier, { offsetY }, headerHeightPx, toolbarHeightPx
//        )
//        Title({ offsetY }, headerHeight)
//    }

//    val screenTotalHeight = 1838f
//    var toolbarHeight by remember { mutableStateOf(213.dp) }
//    val density = LocalDensity.current
//    val toolbarHeightPx = remember(toolbarHeight) { mutableStateOf(toolbarHeight.toPx(density)) }
//    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
//    val contentPaddingPx = remember(toolbarHeight) { mutableStateOf(toolbarHeight.toPx(density)) }
//    val connection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                //println("datmug, $available, ${toolbarOffsetHeightPx.value}, $toolbarHeightPx, $toolbarHeight, ${contentPaddingPx.value}")
//                val delta = available.y
//                val newOffset = toolbarOffsetHeightPx.value + delta
//                toolbarOffsetHeightPx.value = newOffset.coerceIn(Float.NEGATIVE_INFINITY, 0f)
//
//                val newPadding = toolbarHeightPx.value + toolbarOffsetHeightPx.value
//                println("datmug-2, newPadding = ${newPadding}, ${toolbarHeightPx.value}")
//                contentPaddingPx.value = newPadding.coerceIn(0f, toolbarHeightPx.value)
//                return if (contentPaddingPx.value - 100 > 0f) {
//                    available
//                } else {
//                    Offset.Zero
//                }
////                return
//            }
//        }
//    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .nestedScroll(connection),
//    ) {
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .onGloballyPositioned {
//                    with(density) {
//                        //toolbarHeight = it.size.height.toDp()
//                    }
//                }
//                .verticalScroll(rememberScrollState())
//                .offset { IntOffset(x = 0, y = (toolbarOffsetHeightPx.value).roundToInt()) },
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.blue), contentDescription = "",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp),
//                contentScale = ContentScale.Crop
//            )
//            Text(text = "Toolbar")
//        }
//
//        Box(
//            modifier = Modifier
//                .wrapContentSize()
//                .background(color = Color.White)
//        ) {
//            Text(text = "${toolbarOffsetHeightPx.value.roundToInt()}, ${toolbarHeightPx.value}, $toolbarHeight, ${contentPaddingPx.value.roundToInt()}")
//        }
//    }
}

private val headerHeight = 700.dp
private val headerHeight2 = 700.dp

@Composable
private fun Header(offsetY: () -> Float, headerHeight: Dp, heightMeasured: (IntSize) -> Unit) {
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .onGloballyPositioned {
                heightMeasured(it.size)
            }
            .graphicsLayer {
                translationY = -offsetY().toFloat() / 1f // Parallax effect
                alpha = (-1f / headerHeightPx) * offsetY() + 1
            }
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

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4 // to wrap the title only
                    )
                )
        )
    }
}

@Composable
private fun Body(offsetY: () -> Float,  headerHeight: Dp) {
    val density = LocalDensity.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
//            .graphicsLayer {
//                translationY = density.run { headerHeight.toPx() } - offsetY()
//            },
    ) {
        val list = (0..75).map { it.toString() }
        item {
            Spacer(modifier = Modifier.height(headerHeight))
        }
        items(count = list.size) {
            var count by remember {
                mutableStateOf(0)
            }
            TextButton(onClick = { count += 1 }) {
                Text(
                    text = "${list[it]} - $count",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }

//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .verticalScroll(scroll)
//            .graphicsLayer {
//                translationY = density.run { headerHeight.toPx() }
//            }
//    ) {
//        repeat(5) {
//            Text(
//                text = remember {
//                    buildString {
//                        append("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
//                        append("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
//                        append("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
//                    }
//                },
//                style = MaterialTheme.typography.bodyLarge,
//                textAlign = TextAlign.Justify,
//                modifier = Modifier
//                    .background(Color(0XFF161616))
//                    .padding(16.dp),
//                color = Color.White
//            )
//        }
//    }
}

@Composable
private fun Title(
    offsetY: () -> Float,
    headerHeight: Dp,
    modifier: Modifier = Modifier
) {
    var titleHeightPx by remember { mutableStateOf(0f) }
    var titleWidthPx by remember { mutableStateOf(0f) }

    Text(
        text = "Title",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeight.toPx() - toolbarHeight.toPx())
                val collapseFraction: Float = (offsetY() / collapseRange).coerceIn(0f, 1f)

                val scaleXY = lerp(
                    titleFontScaleStart.dp,
                    titleFontScaleEnd.dp,
                    collapseFraction
                )

                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f

                val titleYFirstInterpolatedPoint = lerp(
                    headerHeight - titleHeightPx.toDp() - paddingMedium,
                    headerHeight / 2,
                    collapseFraction
                )

                val titleXFirstInterpolatedPoint = lerp(
                    titlePaddingStart,
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    collapseFraction
                )

                val titleYSecondInterpolatedPoint = lerp(
                    headerHeight / 2,
                    toolbarHeight / 2 - titleHeightPx.toDp() / 2,
                    collapseFraction
                )

                val titleXSecondInterpolatedPoint = lerp(
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    titlePaddingEnd - titleExtraStartPadding,
                    collapseFraction
                )

                val titleY = lerp(
                    titleYFirstInterpolatedPoint,
                    titleYSecondInterpolatedPoint,
                    collapseFraction
                )

                val titleX = lerp(
                    titleXFirstInterpolatedPoint,
                    titleXSecondInterpolatedPoint,
                    collapseFraction
                )

                translationY = titleY.toPx()
                translationX = titleX.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
                titleWidthPx = it.size.width.toFloat()
            }
    )
}

@Composable
private fun Toolbar(
    modifier: Modifier = Modifier, offsetY: () -> Float,
    headerHeightPx: Float, toolbarHeightPx: Float
) {
    val toolbarBottom by remember {
        mutableStateOf(headerHeightPx - toolbarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            offsetY() >= toolbarBottom
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xff026586), Color(0xff032C45))
                )
            ),
            navigationIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            title = {},
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
//        backgroundColor = Color.Transparent,
//        elevation = 0.dp
        )
    }

}