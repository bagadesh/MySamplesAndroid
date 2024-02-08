package com.bagadesh.myallsampleapps.mainScreen.paging3.lazyLoad

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bagadesh on 01/12/22.
 */
class CustomLazyViewHolder constructor(context: Context, private val composeView: ComposeView = ComposeView(context)): RecyclerView.ViewHolder(composeView) {

    init {
        // This is from the previous guidance
        // NOTE: **Do not** do this with Compose 1.2.0-beta02+
        // and RecyclerView 1.3.0-alpha02+
        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )
    }


    fun bind(lazyLoadData: LazyLoadData) {
        composeView.setContent {
            MaterialTheme {
                val d by lazyLoadData.flow.collectAsState(initial = LoadData.Loading)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp), contentAlignment = Alignment.Center
                ) {
                    when(d) {
                        is LoadData.Loaded -> {
                            Button(onClick = {  }) {
                                Text(text = "Data available ${(d as LoadData.Loaded).value}")
                            }
                        }
                        LoadData.Loading -> {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

        }
    }

}