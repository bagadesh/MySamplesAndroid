package com.bagadesh.myallsampleapps.mainScreen.rxJava

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bagadesh.myallsampleapps.others.ActionButton
import com.bagadesh.myallsampleapps.others.TitleBox
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Created by bagadesh on 07/02/23.
 */

@Composable
fun RxJavaSampleUI() {
    val viewModel = viewModel<RxJavaViewModel>()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "HashCode ${viewModel.hashCode()}")
        ActionButton("Subscribe", action = {
            viewModel.addFirst()
        }) {
            TitleBox(title = it) {
                Text(text = viewModel.first.value)
            }
        }
        ActionButton("2nd Subscribe", action = {
//            viewModel.addFirst()
        }) {
            TitleBox(title = it) {
                Text(text = viewModel.first.value)
            }
        }
        ActionButton("take(1) Subscribe", action = {
            viewModel.addSecond()
        }) {
            TitleBox(title = it) {
                Text(text = viewModel.second.value)
            }
        }
        ActionButton("blockingFirst", action = {
            viewModel.addThird()
        }) {
            TitleBox(title = it) {
                Text(text = viewModel.third.value)
            }
        }
        ActionButton("blockingLast", action = {
            viewModel.addForth()
        }) {
            TitleBox(title = it) {
                Text(text = viewModel.forth.value)
            }
        }
        ActionButton("subscribe then dispose", action = {
            viewModel.addOneTimeSubscribe()
        }) {
            TitleBox(title = it) {
                Text(text = viewModel.addOneTimeSubscribe.value)
            }
        }
        ActionButton("withLatestFrom", action = {
            viewModel.withLatestFrom()
        }) {
            TitleBox(title = it) {
                Column {
                    Text(text = viewModel.withLatestFrom.value)
                    Text(text = viewModel.testOutput.value)
                }
            }
        }
        ActionButton("withLatestFromUsingSubscribeOn", action = {
            viewModel.withLatestFromUsingSubscribeOn()
        }) {
            TitleBox(title = it) {
                Column {
                    Text(text = viewModel.withLatestFromUsingSubscribeOn.value)
                    Text(text = viewModel.testOutput.value)
                    Text(text = viewModel.filter.value)
                    Text(text = viewModel.doOnNext.value)
                    Text(text = viewModel.doOnSubscribe.value)
                }
            }
        }
        ActionButton("defer", action = {
            viewModel.defer()
        }) {
            TitleBox(title = it) {
                Column {
                    Text(text = viewModel.defer.value)
                    Text(text = viewModel.deferValue.value)
                }
            }
        }
    }
}
