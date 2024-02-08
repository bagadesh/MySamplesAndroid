package com.bagadesh.myallsampleapps.mainScreen.rxJavaFlow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bagadesh.myallsampleapps.mainScreen.rxJava.RxJavaViewModel
import com.bagadesh.myallsampleapps.others.ActionButton
import com.bagadesh.myallsampleapps.others.TitleBox

/**
 * Created by bagadesh on 23/02/23.
 */

@Composable
fun RxJavaAndFlowUI() {
    val vm = viewModel<RxJavaViewModel>()

    val n1 by vm.n1
    val n2 by vm.n2
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionButton("addNormal", action = {
            vm.adNormalSub()
        }) {
            TitleBox(title = it) {
                Text(text = n2)
            }
        }
        ActionButton("addFlow2", action = {
            vm.addFlow1()
        }) {
            TitleBox(title = it) {
                Text(text = n1)
            }
        }

    }
}