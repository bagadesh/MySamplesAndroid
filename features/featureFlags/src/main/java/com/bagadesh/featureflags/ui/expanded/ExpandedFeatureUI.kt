package com.bagadesh.featureflags.ui.expanded

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagadesh.featureflags.R

/**
 * Created by bagadesh on 14/04/23.
 */

@Composable
fun ExpandedVariablesBackground(
    list: List<Pair<String, Any>>,
    expanded: Boolean,
    content: @Composable (Pair<String, Any>) -> Unit
) {
    AnimatedVisibility(
        visible = expanded
    ) {
        if (list.isEmpty()) {
            ShowNoVariablesUI()
        } else {
            val focusManager = LocalFocusManager.current
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        focusManager.clearFocus()
                    }
            ) {
                Text(
                    text = "Feature variables\n",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(start = 10.dp)
                )

                list.forEachIndexed { index, pair ->
                    content(pair)
                }
            }
        }
    }
}

@Composable
fun ShowFeatureKeyDetailsUI(
    key: String,
    value: Any,
    onValueChange: (String) -> Unit,
    onFocusChange: (isFocused: Boolean) -> Unit
) {
    var variableValue by remember(value) { mutableStateOf(value.toString()) }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = variableValue,
        onValueChange = {
            variableValue = it
            onValueChange(it)
        },
        label = {
            Text(
                text = key,
                modifier = Modifier,
                color = MaterialTheme.colors.onSurface
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            autoCorrect = false
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .onFocusChanged {
                onFocusChange(it.isFocused)
            },
        trailingIcon = {
            CopyButton(value = variableValue)
        }
    )
}

@Composable
fun ShowNoVariablesUI() {
    Text(
        text = "No variables Available",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontWeight = FontWeight.Black
    )
}

@Composable
fun CopyButton(
    value: String
) {
    val localClipboardManager = LocalClipboardManager.current
    IconButton(
        onClick = {
            localClipboardManager.setText(AnnotatedString(value))
        },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
            contentDescription = "Copy",
        )
    }
}