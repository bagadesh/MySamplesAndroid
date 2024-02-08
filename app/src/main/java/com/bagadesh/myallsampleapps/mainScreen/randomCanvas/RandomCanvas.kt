@file:OptIn(ExperimentalComposeUiApi::class)

package com.bagadesh.myallsampleapps.mainScreen.randomCanvas

import androidx.compose.ui.ExperimentalComposeUiApi

/**
 * Created by bagadesh on 23/11/22.
 */
//
//@Composable
//fun RandomCanvas() {
//
//    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//        val infiniteTransition = rememberInfiniteTransition()
//        val degree by infiniteTransition.animateFloat(
//            initialValue = 0f,
//            targetValue = 360f,
//            animationSpec = infiniteRepeatable(
//                animation = tween(5000, easing = LinearEasing),
//                repeatMode = RepeatMode.Restart
//            )
//        )
//        Canvas(modifier = Modifier
//            .size(100.dp)
//            .rotate(degree), onDraw = {
//            drawPath(
//                path = Path().apply {
//                    moveTo(x = size.width / 2, 0f)
//                    lineTo(x = size.width, size.height)
//                    lineTo(x = 0f, size.height)
//                    close()
//                },
//                color = Color.Blue
//            )
//            val value1 = 20f
//            drawPath(
//                path = Path().apply {
//                    moveTo(x = size.width / 2 + value1, 0f)
//                    lineTo(x = size.width, size.height + value1)
//                    lineTo(x = 0f, size.height - value1)
//                    close()
//                },
//                color = Color.Red
//            )
//        })
//        Canvas(modifier = Modifier.size(100.dp)) {
//            drawArc(
//                color = Color.Blue,
//                startAngle = 0f,
//                sweepAngle = 90f,
//                useCenter = true
//            )
//        }
//       // var points: MutableList<Offset> by remember { mutableStateOf(mutableListOf()) }
//        var points = remember { mutableStateListOf<Offset>() }
//
////        Box(modifier = Modifier.fillMaxSize()
////            .drawBehind {
////                points.forEach {
////                    drawCircle(
////                        color = Color.Red,
////                        radius = 10f,
////                        center = it
////                    )
////                }
////            }
////            .pointerInput(Unit) {
////            detectDragGestures(
////                onDragStart = {
////                    points.clear()
////                    points.add(it)
////                    println("datmug, onDragStart ${it}")
////                },
////                onDrag = {change, dragAmount ->
////                    points.add(change.position)
////                    println("datmug, dragAmount ${dragAmount}")
////                }
////            )
////        })
//
//        Box(modifier = Modifier.fillMaxSize()) {
//            CraneDemo()
//            //SharedElementExplorationDemo()
//            //ScreenSizeChangeDemo()
////            NestedSceneHostDemo()
//           // LookaheadWithMovableContentDemo()
//        }
//    }
//}
//
//@Composable
//fun CraneDemo() {
//    val avatar = remember {
//        movableContentWithReceiverOf<SceneScope> {
//            Box(
//                Modifier
//                    .sharedElement()
//                    .background(Color(0xffff6f69), RoundedCornerShape(20))
//                    .fillMaxSize()
//            )
//        }
//    }
//
//    val parent = remember {
//        movableContentWithReceiverOf<SceneScope, @Composable () -> Unit> { child ->
//            Surface(
//                modifier = Modifier
//                    .sharedElement()
//                    .background(Color(0xfffdedac)),
//                color = Color(0xfffdedac),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                child()
//            }
//        }
//    }
//
//    var fullScreen by remember { mutableStateOf(false) }
//    Box(
//        Modifier
//            .fillMaxSize()
//            .padding(10.dp)
//            .clickable { fullScreen = !fullScreen },
//        contentAlignment = Alignment.Center
//    ) {
//        SceneHost(Modifier.fillMaxSize()) {
//            if (fullScreen) {
//                Box(Modifier.offset(100.dp, 150.dp)) {
//                    parent {
//                        Box(
//                            Modifier
//                                .padding(10.dp)
//                                .wrapContentSize(Alignment.Center)
//                                .size(50.dp)
//                        ) {
//                            avatar()
//                        }
//                    }
//                }
//            } else {
//                parent {
//                    Column(Modifier.fillMaxSize()) {
//                        val alpha = produceState(0f) {
//                            animate(0f, 1f, animationSpec = tween(200)) { value, _ ->
//                                this.value = value
//                            }
//                        }
//                        Box(
//                            Modifier
//                                .fillMaxWidth()
//                                .height(300.dp)
//                                .graphicsLayer {
//                                    this.alpha = alpha.value
//                                }
//                                .background(Color.DarkGray)
//                                .animateContentSize())
//                        Box(
//                            Modifier
//                                .padding(10.dp)
//                                .size(60.dp)
//                        ) {
//                            avatar()
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun SceneHost(modifier: Modifier = Modifier, content: @Composable SceneScope.() -> Unit) {
////    LookaheadLayout(
////        modifier = modifier,
////        content = {
////            val sceneScope = remember { SceneScope(this) }
////            sceneScope.content()
////        },
////        measurePolicy = { measurables, constraints ->
////            val placeables = measurables.map { it.measure(constraints) }
////            val maxWidth: Int = placeables.maxOf { it.width }
////            val maxHeight = placeables.maxOf { it.height }
////            // Position the children.
////            layout(maxWidth, maxHeight) {
////                placeables.forEach {
////                    it.place(0, 0)
////                }
////            }
////        })
//}
//private const val debugSharedElement = false
//class SceneScope internal constructor(
//    lookaheadLayoutScope: LookaheadLayoutScope
//) : LookaheadLayoutScope by lookaheadLayoutScope {
//    fun Modifier.sharedElement(): Modifier = composed {
//        var offsetAnimation: Animatable<IntOffset, AnimationVector2D>?
//                by remember { mutableStateOf(null) }
//        var sizeAnimation: Animatable<IntSize, AnimationVector2D>?
//                by remember { mutableStateOf(null) }
//        var placementOffset: IntOffset by remember { mutableStateOf(IntOffset.Zero) }
//        var targetOffset: IntOffset? by remember {
//            mutableStateOf(null)
//        }
//        var targetSize: IntSize? by remember {
//            mutableStateOf(null)
//        }
//        LaunchedEffect(Unit) {
//            launch {
//                snapshotFlow {
//                    targetOffset
//                }.collect { target ->
//                    if (target != null && target != offsetAnimation?.targetValue) {
//                        offsetAnimation?.run {
//                            launch { animateTo(target) }
//                        } ?: Animatable(target, IntOffset.VectorConverter).let {
//                            offsetAnimation = it
//                        }
//                    }
//                }
//            }
//            launch {
//                snapshotFlow {
//                    targetSize
//                }.collect { target ->
//                    if (target != null && target != sizeAnimation?.targetValue) {
//                        sizeAnimation?.run {
//                            launch { animateTo(target) }
//                        } ?: Animatable(target, IntSize.VectorConverter).let {
//                            sizeAnimation = it
//                        }
//                    }
//                }
//            }
//        }
//        this
//            .onPlaced { lookaheadScopeCoordinates, layoutCoordinates ->
//                targetOffset =
//                    lookaheadScopeCoordinates.localLookaheadPositionOf(layoutCoordinates).round()
//                placementOffset = lookaheadScopeCoordinates.localPositionOf(
//                    layoutCoordinates, Offset.Zero
//                ).round()
//            }
//            .drawBehind {
//                if (debugSharedElement) {
//                    drawRect(
//                        color = Color.Black,
//                        style = Stroke(2f),
//                        topLeft = (targetOffset!! - placementOffset).toOffset(),
//                        size = targetSize!!.toSize()
//                    )
//                }
//            }
//            .intermediateLayout { measurable, _, lookaheadSize ->
//                targetSize = lookaheadSize
//                val (width, height) = sizeAnimation?.value ?: lookaheadSize
//                val animatedConstraints = Constraints(
//                    minWidth = width,
//                    maxWidth = width,
//                    minHeight = height,
//                    maxHeight = height
//                )
//                val placeable = measurable.measure(animatedConstraints)
//                layout(placeable.width, placeable.height) {
//                    val (x, y) = offsetAnimation?.run {
//                        value - placementOffset
//                    } ?: (targetOffset!! - placementOffset)
//                    placeable.place(x, y)
//                }
//            }
//    }
//    fun Modifier.animateSizeAndSkipToFinalLayout() = composed {
//        var sizeAnimation: Animatable<IntSize, AnimationVector2D>? by remember {
//            mutableStateOf(null)
//        }
//        var targetSize: IntSize? by remember { mutableStateOf(null) }
//        LaunchedEffect(Unit) {
//            snapshotFlow { targetSize }.collect { target ->
//                if (target != null && target != sizeAnimation?.targetValue) {
//                    sizeAnimation?.run {
//                        launch { animateTo(target) }
//                    } ?: Animatable(target, IntSize.VectorConverter).let {
//                        sizeAnimation = it
//                    }
//                }
//            }
//        }
//        this
//            .drawBehind {
//                if (debugSharedElement) {
//                    drawRect(
//                        color = Color.Black,
//                        style = Stroke(2f),
//                        topLeft = Offset.Zero,
//                        size = targetSize!!.toSize()
//                    )
//                }
//            }
//            .intermediateLayout { measurable, constraints, lookaheadSize ->
//                targetSize = lookaheadSize
//                val (width, height) = sizeAnimation?.value ?: lookaheadSize
//                val placeable = measurable.measure(
//                    Constraints.fixed(lookaheadSize.width, lookaheadSize.height)
//                )
//                // Make sure the content is aligned to topStart
//                val wrapperWidth = width.coerceIn(constraints.minWidth, constraints.maxWidth)
//                val wrapperHeight = height.coerceIn(constraints.minHeight, constraints.maxHeight)
//                layout(width, height) {
//                    placeable.place(-(wrapperWidth - width) / 2, -(wrapperHeight - height) / 2)
//                }
//            }
//    }
//}