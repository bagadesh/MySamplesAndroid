package com.bagadesh.myallsampleapps.mainScreen.lazylazy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by bagadesh on 07/02/24.
 */


val core = buildList {
    repeat(10) {
        add(buildList {
            repeat(10) {
                add(Unit)
            }
        })
    }
}

val azList = buildList {
    repeat(50) {
        add(Unit)
    }
}

@Composable
internal fun LazyLazyColumn() {

    val azCollection: MutableState<List<Unit>?> = remember {
        mutableStateOf(null)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
    ) {

        items(
            span = {
                GridItemSpan(maxLineSpan)
            },
            count = core.size
        ) {
            val list = core[it]
            if (it == core.size - 1) {
                azCollection.value = azList
            }
            RowMock(modifier = Modifier, list = list)
        }
//
//        item {
//            val list = core.last()
//            ColumnMock(modifier = Modifier, list = list)
//        }
//
        item(
            span = {
                GridItemSpan(maxLineSpan)
            },
        ) {
            Text(text = "A-Z collection")
        }

        items(
            count = azCollection.value?.size ?: 0
        ) {
            Content()
        }
    }

}


@Composable
internal fun ColumnMock(modifier: Modifier, list: List<Unit>) {
    Column(
        modifier = Modifier
    ) {
        Text(text = "A-Z collection")
        LazyColumn(
            modifier.fillMaxWidth()
        ) {
            items(count = list.size) {
                Content()
            }
        }
    }


}

@Composable
internal fun RowMock(modifier: Modifier, list: List<Unit>) {
    Column(
        modifier = Modifier
    ) {
        Text(text = "Title")
        LazyRow(
            modifier.fillMaxWidth()
        ) {
            items(count = list.size) {
                Content()
            }
        }
    }


}

@Composable
internal fun Content() {
    Box(contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp, 100.dp)
                .background(Color.Blue.copy(0.5f))
                .blur(10.dp)
        )
    }
}