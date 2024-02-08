@file:OptIn(ExperimentalTextApi::class)

package com.bagadesh.exposed.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawTransform
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.reflect.full.memberProperties

/**
 * Created by bagadesh on 11/04/23.
 */

private val borderSize = RoundedCornerShape(10.dp)

@Composable
fun MaterialTheme.ShowColors() {
    val colorSchemeMembers = ColorScheme::class.memberProperties
    LazyRow {
        items(colorSchemeMembers.toList()) {
            ColorBox(
                color = it.get(colorScheme) as Color
            ) {
                ColorText(
                    text = it.name, color = colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun ColorBox(
    color: Color, content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 1.dp)
            .heightIn(min = 30.dp)
            .widthIn(min = 50.dp)
            .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = borderSize)
            .clip(borderSize)
            .background(color)
            .padding(5.dp)
        ,
        contentAlignment = Alignment.Center
//            .layout { measurable, constraints ->
//                val placeableList = measurable.measure(constraints)
//                println("datmug, ${placeableList.width}, ${placeableList.height}, constraints=  $constraints")
//                layout(constraints.minWidth, constraints.minHeight) {
//                    placeableList.place(0, 0)
//                }
//            },

    ) {
        content()
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ColorText(
    text: String,
    color: Color,
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = color,
        modifier = Modifier.padding(5.dp)
    )
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.White,
        modifier = Modifier.padding(5.dp)
    )
}

fun DrawScope.drawCustomText(
    textMeasurer: TextMeasurer,
    text: String,
    topLeft: Offset = Offset.Zero,
    style: TextStyle = TextStyle.Default,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    size: Size = Size.Unspecified,
    blendMode: BlendMode = DrawScope.DefaultBlendMode
) {
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text),
        style = style,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        constraints = Constraints(),
        layoutDirection = layoutDirection,
        density = this
    )

    withTransform({
        translate(topLeft.x, topLeft.y)
        clip(textLayoutResult)
    }) {
        textLayoutResult.multiParagraph.paint(
            canvas = drawContext.canvas,
            blendMode = blendMode
        )
    }
}

private fun DrawScope.textLayoutConstraints(
    size: Size,
    topLeft: Offset
): Constraints {
    val minWidth: Int
    val maxWidth: Int
    val isWidthNaN = size.isUnspecified || size.width.isNaN()
    if (isWidthNaN) {
        minWidth = 0
        maxWidth = ceil(this.size.width - topLeft.x).roundToInt()
    } else {
        val fixedWidth = ceil(size.width).roundToInt()
        minWidth = fixedWidth
        maxWidth = fixedWidth
    }

    val minHeight: Int
    val maxHeight: Int
    val isHeightNaN = size.isUnspecified || size.height.isNaN()
    if (isHeightNaN) {
        minHeight = 0
        maxHeight = ceil(this.size.height - topLeft.y).roundToInt()
    } else {
        val fixedHeight = ceil(size.height).roundToInt()
        minHeight = fixedHeight
        maxHeight = fixedHeight
    }

    return Constraints(minWidth, maxWidth, minHeight, maxHeight)
}

private fun DrawTransform.clip(textLayoutResult: TextLayoutResult) {
    if (textLayoutResult.hasVisualOverflow &&
        textLayoutResult.layoutInput.overflow != TextOverflow.Visible
    ) {
        clipRect(
            left = 0f,
            top = 0f,
            right = textLayoutResult.size.width.toFloat(),
            bottom = textLayoutResult.size.height.toFloat()
        )
    }
}