package com.bagadesh.myallsampleapps.mainScreen.changeEnvironment

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by bagadesh on 20/11/22.
 */

enum class Environment(val shortDisplay: String) {
    PROD("Prod"), QA("QA"), INT("INT")
}

@Composable
fun ChangeEnvironmentComponent(
    currentEnvironment: Environment,
    onEnvironmentChanged: (Environment) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedEnvironment by remember { mutableStateOf(currentEnvironment) }
    val remainingEnvironments by remember {
        derivedStateOf {
            Environment.values().filter { it != selectedEnvironment }
        }
    }
    val firstEnvironment = remember(remainingEnvironments) { remainingEnvironments[0] }
    val secondEnvironment = remember(remainingEnvironments) { remainingEnvironments[1] }

    Box(modifier = Modifier.fillMaxWidth().wrapContentHeight(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.onSurface)
                .wrapContentSize()
                .clickable {
                    expanded = !expanded
                }
                .padding(20.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AnimatedVisibility(visible = expanded) {
                EnvironmentButton(text = firstEnvironment.shortDisplay) {
                    selectedEnvironment = firstEnvironment
                    expanded = !expanded
                    onEnvironmentChanged(selectedEnvironment)
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier, verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = selectedEnvironment.shortDisplay, color = MaterialTheme.colorScheme.surface, fontSize = 20.sp, modifier = Modifier, textAlign = TextAlign.Center)
                Text(text = "Environment", color = MaterialTheme.colorScheme.surface, fontSize = 12.sp)
                Text(text = "Override", color = MaterialTheme.colorScheme.surface, fontSize = 12.sp)
            }
            AnimatedVisibility(visible = expanded) {
                EnvironmentButton(text = secondEnvironment.shortDisplay) {
                    selectedEnvironment = secondEnvironment
                    expanded = !expanded
                    onEnvironmentChanged(selectedEnvironment)
                }
            }
        }
    }
}


@Composable
fun EnvironmentButton(text: String, onClick: () -> Unit) {
    Row {
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .width(80.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.Black)
                .clickable { onClick() }
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
        }
        Spacer(modifier = Modifier.size(20.dp))
    }
}
