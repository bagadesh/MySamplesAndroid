//package com.bagadesh.myallsampleapps.mainScreen.randomCanvas.google
//
///**
// * Created by bagadesh on 03/12/22.
// */
//
//import androidx.compose.animation.animateColor
//import androidx.compose.animation.core.InfiniteTransition
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.StartOffset
//import androidx.compose.animation.core.StartOffsetType
//import androidx.compose.animation.core.animate
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.movableContentWithReceiverOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.bagadesh.myallsampleapps.mainScreen.randomCanvas.SceneHost
//import com.bagadesh.myallsampleapps.mainScreen.randomCanvas.SceneScope
//
//@Composable
//fun LookaheadWithMovableContentDemo() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        var isSingleColumn by remember { mutableStateOf(true) }
//
//        Column(
//            Modifier.padding(100.dp).fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row(modifier = Modifier.clickable {
//                isSingleColumn = true
//            }, verticalAlignment = Alignment.CenterVertically) {
//                RadioButton(isSingleColumn, { isSingleColumn = true })
//                Text("Single Column")
//            }
//            Row(modifier = Modifier.clickable {
//                isSingleColumn = false
//            }, verticalAlignment = Alignment.CenterVertically) {
//                RadioButton(!isSingleColumn, { isSingleColumn = false })
//                Text("Double Column")
//            }
//        }
//
//        val items = remember {
//            colors.mapIndexed { id, color ->
//                movableContentWithReceiverOf<SceneScope, Float> { weight ->
//                    Box(
//                        Modifier.padding(15.dp).height(80.dp)
//                            .fillMaxWidth(weight)
//                            .sharedElement()
//                            .background(color, RoundedCornerShape(20)),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        when (id) {
//                            0 -> CircularProgressIndicator(color = Color.White)
//                            1 -> Box(Modifier.graphicsLayer {
//                                scaleX = 0.5f
//                                scaleY = 0.5f
//                                translationX = 100f
//                            }) {
//                                AnimatedDotsDemo()
//                            }
//                            2 -> Box(Modifier.graphicsLayer {
//                                scaleX = 0.5f
//                                scaleY = 0.5f
//                            }) { InfinitePulsingHeart() }
//                            else -> InfiniteProgress()
//                        }
//                    }
//                }
//            }
//        }
//        SceneHost(Modifier.fillMaxSize()) {
//            if (isSingleColumn) {
//                Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    items.forEach {
//                        it(0.8f)
//                    }
//                }
//            } else {
//                Row {
//                    Column(Modifier.weight(1f)) {
//                        items.forEachIndexed { id, item ->
//                            if (id % 2 == 0) {
//                                item(1f)
//                            }
//                        }
//                    }
//                    Column(Modifier.weight(1f)) {
//                        items.forEachIndexed { id, item ->
//                            if (id % 2 != 0) {
//                                item(1f)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//private val colors = listOf(
//    Color(0xffff6f69),
//    Color(0xffffcc5c),
//    Color(0xff264653),
//    Color(0xff2a9d84)
//)
//
//@Preview
//@Composable
//fun InfiniteAnimationDemo() {
//    val alpha = remember { mutableStateOf(1f) }
//    LaunchedEffect(Unit) {
//        animate(
//            initialValue = 1f,
//            targetValue = 0f,
//            animationSpec = infiniteRepeatable(
//                animation = tween(1000),
//                repeatMode = RepeatMode.Reverse
//            )
//        ) { value, _ ->
//            alpha.value = value
//        }
//    }
//    Box(Modifier.fillMaxSize()) {
//        Icon(
//            Icons.Filled.Favorite,
//            contentDescription = null,
//            modifier = Modifier.align(Alignment.Center)
//                .graphicsLayer(
//                    scaleX = 3.0f,
//                    scaleY = 3.0f,
//                    alpha = alpha.value
//                ),
//            tint = Color.Red
//        )
//    }
//}
//
//@Preview
//@Composable
//fun InfiniteTransitionDemo() {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        InfinitePulsingHeart()
//        Spacer(Modifier.size(200.dp))
//        InfiniteProgress()
//    }
//}
//@Composable
//fun InfinitePulsingHeart() {
//    val infiniteTransition = rememberInfiniteTransition()
//    val scale by infiniteTransition.animateFloat(
//        initialValue = 3f,
//        targetValue = 6f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(1000),
//            repeatMode = RepeatMode.Restart
//        )
//    )
//    val color by infiniteTransition.animateColor(
//        initialValue = Color.Red,
//        targetValue = Color(0xff800000), // Dark Red
//        animationSpec = infiniteRepeatable(
//            animation = tween(1000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//    Icon(
//        Icons.Filled.Favorite,
//        null,
//        Modifier.graphicsLayer(
//            scaleX = scale,
//            scaleY = scale
//        ),
//        tint = color
//    )
//}
//@Composable
//fun InfiniteProgress() {
//    val infiniteTransition = rememberInfiniteTransition()
//    Row {
//        infiniteTransition.PulsingDot(StartOffset(0))
//        infiniteTransition.PulsingDot(StartOffset(150, StartOffsetType.FastForward))
//        infiniteTransition.PulsingDot(StartOffset(300, StartOffsetType.FastForward))
//    }
//}
//@Composable
//fun InfiniteTransition.PulsingDot(startOffset: StartOffset) {
//    val scale by animateFloat(
//        0.2f,
//        1f,
//        infiniteRepeatable(tween(600), RepeatMode.Reverse, initialStartOffset = startOffset)
//    )
//    Box(
//        Modifier.padding(5.dp).size(20.dp).graphicsLayer {
//            scaleX = scale
//            scaleY = scale
//        }.background(Color.Gray, shape = CircleShape)
//    )
//}