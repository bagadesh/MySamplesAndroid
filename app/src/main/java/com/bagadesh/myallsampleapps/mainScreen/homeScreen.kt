package com.bagadesh.myallsampleapps.mainScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.bagadesh.myallsampleapps.features.Features
import kotlin.math.roundToInt

/**
 * Created by bagadesh on 20/11/22.
 */

@Composable
fun HomeScreen(
    onClick: (Features) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        items(count = 200, key = {
            it
        }) { index ->
            val animatable = remember {
                Animatable(initialValue = 0f)
            }
            LaunchedEffect(key1 = Unit, block = {
                animatable.animateTo(1f)
            })
            Column(
                modifier = Modifier
                    .then(FillModifier(direction = Direction.Horizontal, fraction = { animatable.value }, inspectorInfo = {
                        name = "fillMaxSize"
                        properties["fraction"] = animatable.value
                    }))
                    .wrapContentHeight()
            ) {
                val isAvailable = remember { index < features.size }
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clickable {
                            if (isAvailable) {
                                onClick(features[index])
                            }
                        }
                        .wrapContentHeight()
                ) {
                    if (isAvailable) {
                        SelectHeading(text = features[index].displayTitle)
                    } else {
                        SelectHeading(text = "TODO yet to be implemented")
                    }
                }
                Divider()
            }
        }
    }
}


@Composable
fun SelectHeading(text: String) {
    Text(text = text)
}


internal enum class Direction {
    Vertical, Horizontal, Both
}


private class FillModifier(
    private val direction: Direction,
    private val fraction: () -> Float,
    inspectorInfo: InspectorInfo.() -> Unit
) : LayoutModifier, InspectorValueInfo(inspectorInfo) {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val minWidth: Int
        val maxWidth: Int
        if (constraints.hasBoundedWidth && direction != Direction.Vertical) {
            val width = (constraints.maxWidth * fraction()).roundToInt()
                .coerceIn(constraints.minWidth, constraints.maxWidth)
            minWidth = width
            maxWidth = width
        } else {
            minWidth = constraints.minWidth
            maxWidth = constraints.maxWidth
        }
        val minHeight: Int
        val maxHeight: Int
        if (constraints.hasBoundedHeight && direction != Direction.Horizontal) {
            val height = (constraints.maxHeight * fraction()).roundToInt()
                .coerceIn(constraints.minHeight, constraints.maxHeight)
            minHeight = height
            maxHeight = height
        } else {
            minHeight = constraints.minHeight
            maxHeight = constraints.maxHeight
        }
        val placeable = measurable.measure(
            Constraints(minWidth, maxWidth, minHeight, maxHeight)
        )

        return layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }

    override fun equals(other: Any?) =
        other is FillModifier && direction == other.direction && fraction == other.fraction

    override fun hashCode() = direction.hashCode() * 31 + fraction.hashCode()
}