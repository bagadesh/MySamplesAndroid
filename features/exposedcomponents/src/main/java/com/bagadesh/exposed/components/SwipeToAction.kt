package com.bagadesh.exposed.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.bagadesh.exposed.components.SwipeAlignment.*
import kotlin.math.roundToInt

/**
 * Created by bagadesh on 10/04/23.
 */

const val defaultDragAmount = 6

enum class SwipeAlignment { END, START }

@Composable
fun SwipeToAction(
    modifier: Modifier = Modifier,
    swipeAlignment: SwipeAlignment = END,
    drag: Int = defaultDragAmount,
    reveal: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val placeableList = subcompose(RevealSlots.REVEAL) { reveal() }.map { it.measure(constraints) }
        val revealMaxSize = placeableList.fold(IntSize.Zero) { currentMax, placeable ->
            IntSize(
                width = maxOf(currentMax.width, placeable.width),
                height = maxOf(currentMax.height, placeable.height)
            )
        }
        val mainContent = subcompose(RevealSlots.CONTENT) {
            Box(
                modifier = Modifier.swipeToAction(
                    dragAnchor = -drag, revealSize = revealMaxSize.width,
                    swipeAlignment = swipeAlignment
                )
            ) {
                content()
            }
        }.map {
            it.measure(constraints)
        }
        val mainMaxSize = mainContent.fold(IntSize.Zero) { currentMax, placeable ->
            IntSize(
                width = maxOf(currentMax.width, placeable.width),
                height = maxOf(currentMax.height, placeable.height)
            )
        }
        layout(constraints.maxWidth, revealMaxSize.height) {
            when (swipeAlignment) {
                END -> {
                    placeableList.forEach {
                        it.placeRelative(constraints.maxWidth - revealMaxSize.width, (mainMaxSize.height - revealMaxSize.height) / 2)
                    }
                }

                START -> {
                    placeableList.forEach {
                        it.placeRelative(
                            0, (mainMaxSize.height - revealMaxSize.height) / 2
                        )
                    }
                }
            }

            mainContent.forEach {
                it.placeRelative(0, 0)
            }
        }
    }
}

enum class RevealSlots {
    REVEAL, CONTENT
}

fun Modifier.swipeToAction(
    dragAnchor: Int = defaultDragAmount,
    swipeAlignment: SwipeAlignment,
    revealSize: Int
): Modifier = composed {
    val targetValueWhenStateTrue: Float
    val targetValueWhenStateFalse: Float
    when (swipeAlignment) {
        END -> {
            targetValueWhenStateTrue = -revealSize.toFloat()
            targetValueWhenStateFalse = 0f
        }

        START -> {
//            targetValueWhenStateTrue = 0f
//            targetValueWhenStateFalse = -revealSize.toFloat()

            targetValueWhenStateTrue = -revealSize.toFloat()
            targetValueWhenStateFalse = 0f
        }
    }
    val transitionState = remember { MutableTransitionState(false) }
    val transition = updateTransition(transitionState, "updateTransition")
    val offsetTransition by transition.animateFloat(
        label = "offsetTransition",
        transitionSpec = { tween(durationMillis = 500) },
        targetValueByState = {
            println("datmug, targetValueByState $it")
            if (it) targetValueWhenStateTrue else targetValueWhenStateFalse
        },
    )
    offset {
        IntOffset(x = offsetTransition.roundToInt(), y = 0)
    }.pointerInput(Unit) {
        detectHorizontalDragGestures { _, dragAmount ->
            when {
                dragAmount > dragAnchor -> {
                    // Swipe Left to Right increase dragAmount
                    transitionState.targetState = false
                }

                dragAmount < -dragAnchor -> {
                    // Swipe Right to Left decreases dragAmount
                    transitionState.targetState = true
                }
            }
        }
    }
}