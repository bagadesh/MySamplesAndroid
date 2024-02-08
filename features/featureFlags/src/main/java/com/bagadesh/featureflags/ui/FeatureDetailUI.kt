package com.bagadesh.featureflags.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by bagadesh on 14/04/23.
 */

@Composable
fun FeatureDetailUI(
    title: String,
    description: String,
    expanded: Boolean,
    isChecked: Boolean,
    contentClick: () -> Unit,
    onCheckBoxClicked: (Boolean) -> Unit,
    iconButtonClick: () -> Unit,
    expandedContent: (@Composable () -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize()
            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
            .padding(0.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            val rotateDegree by animateFloatAsState(
                targetValue = if (!expanded) 0f else 180f,
                animationSpec = tween(durationMillis = 200), label = "rotateDegree"
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        contentClick()
                    }
                    .padding(3.dp)
            ) {
                IconButton(
                    onClick = {
                        iconButtonClick()
                    },
                    modifier = Modifier.align(Alignment.Top)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = "Expand Arrow Icon",
                        modifier = Modifier
                            .size(48.dp, 48.dp)
                            .rotate(rotateDegree)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 10.dp),
                ) {
                    Text(
                        text = title,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(5.dp)
                    )
                    AnimatedVisibility(
                        visible = expanded && description.isNotEmpty()
                    ) {
                        Text(
                            text = description,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = onCheckBoxClicked,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
            expandedContent?.invoke()
        }
    }
}