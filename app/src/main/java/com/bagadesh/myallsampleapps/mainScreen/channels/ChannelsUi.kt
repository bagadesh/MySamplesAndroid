package com.bagadesh.myallsampleapps.mainScreen.channels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.bagadesh.myallsampleapps.compoents.CustomButton
import kotlinx.coroutines.launch

/**
 * Created by bagadesh on 27/12/23.
 */

@Composable
fun ChannelsUi() {

    val sampler = remember {
        ChannelSample()
    }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        CustomButton(text = "Send") {
            scope.launch {
                sampler.send()
            }
        }

        CustomButton(text = "Receive") {
            scope.launch {
                sampler.receive()
            }
        }

        CustomButton(text = "Close") {
            scope.launch {
                sampler.channel.close()
            }
        }
    }
}