package com.bagadesh.exposed.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.IntSize

/**
 * Created by bagadesh on 31/05/23.
 */

@Composable
fun SwipeToSlide(
    modifier: Modifier = Modifier,
    swipeAlignment: SwipeAlignment = SwipeAlignment.END,
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
            val transitionState = remember { MutableTransitionState(false) }
            val transition = updateTransition(transitionState, "offsetUpdateTransition")
            val offsetTransition by transition.animateFloat(
                label = "offsetTransition",
                transitionSpec = { tween(durationMillis = 500) },
                targetValueByState = { if (it) (-revealMaxSize.width).toFloat() else 0f },
            )
            val iOffsetTransition by transition.animateFloat(
                label = "iOffsetTransition",
                transitionSpec = { tween(durationMillis = 500) },
                targetValueByState = { if (it) 0f else revealMaxSize.width.toFloat() },
            )

            Box(
                modifier = Modifier
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
                SwipeAlignment.END -> {
                    placeableList.forEach {
                        it.placeRelative(constraints.maxWidth, (mainMaxSize.height - revealMaxSize.height) / 2)
                    }
                }

                SwipeAlignment.START -> {
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