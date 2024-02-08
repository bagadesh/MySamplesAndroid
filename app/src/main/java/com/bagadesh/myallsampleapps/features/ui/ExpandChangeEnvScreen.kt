package com.bagadesh.myallsampleapps.features.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagadesh.myallsampleapps.mainScreen.changeEnvironment.ChangeEnvironmentComponent
import com.bagadesh.myallsampleapps.mainScreen.changeEnvironment.Environment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by bagadesh on 20/11/22.
 */
@Composable
fun ExpandChangeEnvScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        ChangeEnvironmentComponent(
            currentEnvironment = Environment.PROD,
            onEnvironmentChanged = {
                //Update database/sharedPreference
            }
        )
        ResetButtons {

        }
    }
}


enum class ResetButton(val display: String) {
    RESET_ONE_TIME_PREVIEW("RESET ONE TIME PREVIEW"),
    RESET_DAILY_PREVIEW("RESET DAILY PREVIEW"),
    EXPIRE_ONE_TIME_PREVIEW("EXPIRE ONE TIME PREVIEW"),
    EXPIRE_DAILY_PREVIEW("EXPIRE DAILY PREVIEW"),
}

@Composable
fun ResetButtons(onClick: (ResetButton) -> Unit) {
    val list = remember {
        ResetButton.values()
    }
    LazyVerticalGrid(columns = GridCells.Adaptive(80.dp)) {
        items(list) {
            ResetButtonItem(resetButton = it, onClick = onClick)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ResetButtonItem(resetButton: ResetButton, onClick: (ResetButton) -> Unit) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .width(100.dp)
            .requiredHeight(100.dp)
            .background(color = MaterialTheme.colorScheme.onSurface)
            .clickable {
                if (isClicked) {
                    return@clickable
                }
                onClick(resetButton)
                isClicked = true
                scope.launch {
                    delay(1000)
                    isClicked = false
                }
            }
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isClicked,
            exit = scaleOut() + fadeOut(),
            enter = scaleIn() + fadeIn()
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "Done", tint = MaterialTheme.colorScheme.surface)
        }
        AnimatedVisibility(
            visible = !isClicked,
            exit = scaleOut() + fadeOut(),
            enter = scaleIn() + fadeIn()
        ) {
            Text(text = resetButton.display, fontSize = 12.sp, color = MaterialTheme.colorScheme.surface, textAlign = TextAlign.Center)
        }
    }
}
















