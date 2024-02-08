@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.bagadesh.myallsampleapps.mainScreen.autofill

import android.os.Build
import android.view.autofill.AutofillManager
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.Autofill
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/**
 * Created by bagadesh on 16/06/23.
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun AutoFillScreenUi() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), verticalArrangement = Arrangement.Center
    ) {
        EmailField()
//        Spacer(modifier = Modifier.size(10.dp))
//        var password by remember {
//            mutableStateOf("")
//        }
//        AutoFillNodeInsertion(autofillTypes = listOf(AutofillType.Password), onFill = { password = it }) { autoFill, autoFillNode ->
//            Column {
//                TextField(value = password, onValueChange = {
//                    password = it
//                }, placeholder = {
//                    Text(text = "Password")
//                }, modifier = Modifier
//                    .fillMaxWidth()
//                    .autoFillBound(autoFillNode)
//                    .onFocusChanged {
//                        if (it.isFocused) {
//                            autoFill?.requestAutofillForNode(autofillNode = autoFillNode)
//                        } else {
//                            autoFill?.cancelAutofillForNode(autofillNode = autoFillNode)
//                        }
//                    })
//                val lView = LocalView.current
//                Button(onClick = {
//                    val afm = context.getSystemService(AutofillManager::class.java)
//                    afm?.showAutofillDialog(lView, autoFillNode.id)
//                }) {
//                    Text(text = "Custom Click")
//                }
//            }
//        }
//
//
//
//
//        AndroidView(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp),
//            factory = { context ->
//                TextInputLayout(context).also {
//                    it.addView(
//                        TextInputEditText(context).also {
//                            it.hint = "email"
//                            it.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS)
//                            it.setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_YES)
//                            it.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
//                        }
//                    )
//                }
//
//            }) {
//
//        }
    }
}

@Composable
fun EmailField() {
    var email by remember { mutableStateOf("") }
    Column {
        val autoFillHandler = AutoFillRequestHandler(autofillTypes = listOf(AutofillType.EmailAddress),
            onFill = {
                email = it
            }
        )
        TextField(
            value = email,
            onValueChange = {
                email = it
                if (it.isEmpty()) autoFillHandler.requestVerifyManual()
            },
            placeholder = {
                Text(text = "Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .connectNode(handler = autoFillHandler)
                .defaultFocusChangeAutoFill(handler = autoFillHandler)
        )
        val focusManager = LocalFocusManager.current
        Button(onClick = { autoFillHandler.requestManual() }) {
            Text(text = "Show Auto Fill")
        }
        Button(onClick = { focusManager.clearFocus() }) {
            Text(text = "Clear Focus")
        }
    }

}

fun Modifier.connectNode(handler: AutoFillHandler): Modifier {
    return with(handler) { fillBounds() }
}

fun Modifier.defaultFocusChangeAutoFill(handler: AutoFillHandler): Modifier {
    return this.then(
        Modifier.onFocusChanged {
            if (it.isFocused) {
                handler.request()
            } else {
                handler.cancel()
            }
        }
    )
}

@Composable
fun AutoFillRequestHandler(
    autofillTypes: List<AutofillType> = listOf(),
    onFill: (String) -> Unit,
): AutoFillHandler {
    val view = LocalView.current
    val context = LocalContext.current
    var isFillRecently = remember { false }
    val autoFillNode = remember {
        AutofillNode(
            autofillTypes = autofillTypes,
            onFill = {
                isFillRecently = true
                onFill(it)
            }
        )
    }
    val autofill = LocalAutofill.current
    LocalAutofillTree.current += autoFillNode
    return remember {
        object : AutoFillHandler {
            val autofillManager = context.getSystemService(AutofillManager::class.java)
            override fun requestManual() {
                autofillManager.requestAutofill(
                    view,
                    autoFillNode.id,
                    autoFillNode.boundingBox?.toAndroidRect() ?: error("BoundingBox is not provided yet")
                )
            }

            override fun requestVerifyManual() {
                if (isFillRecently) {
                    isFillRecently = false
                    requestManual()
                }
            }

            override val autoFill: Autofill?
                get() = autofill

            override val autoFillNode: AutofillNode
                get() = autoFillNode

            override fun request() {
                autofill?.requestAutofillForNode(autofillNode = autoFillNode)
            }

            override fun cancel() {
                autofill?.cancelAutofillForNode(autofillNode = autoFillNode)
            }

            override fun Modifier.fillBounds(): Modifier {
                return this.then(
                    Modifier.onGloballyPositioned {
                        autoFillNode.boundingBox = it.boundsInWindow()
                    })
            }
        }
    }
}

fun Rect.toAndroidRect(): android.graphics.Rect {
    return android.graphics.Rect(
        left.roundToInt(),
        top.roundToInt(),
        right.roundToInt(),
        bottom.roundToInt()
    )
}

interface AutoFillHandler {
    val autoFill: Autofill?
    val autoFillNode: AutofillNode
    fun requestVerifyManual()
    fun requestManual()
    fun request()
    fun cancel()
    fun Modifier.fillBounds(): Modifier
}
