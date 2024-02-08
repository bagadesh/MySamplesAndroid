@file:OptIn(ExperimentalMaterial3Api::class)

package com.bagadesh.myallsampleapps.mainScreen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagadesh.myallsampleapps.features.Features
import com.bagadesh.myallsampleapps.ui.theme.MyAllSampleAppsTheme

/**
 * Created by bagadesh on 02/03/23.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenCardStyle(
    result: List<Features>,
    onClick: (Features) -> Unit
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = result) {
            HomeScreenCard(
                modifier = Modifier.animateItemPlacement(),
                title = it.displayTitle,
                onClick = {
                    onClick(it)
                },
                onFavoriteClick = {

                }
            )
        }
    }

}

@Preview
@Composable
fun HomeScreenCardPreview() {
    MyAllSampleAppsTheme(
        darkTheme = true
    ) {
        HomeScreenCard(
            title = "Title Long Title Long Title Long Title",
            onClick = {},
            onFavoriteClick = {}
        )
    }
}

@Composable
fun HomeScreenCard(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier then Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .requiredHeight(100.dp)
                .background(color = MaterialTheme.colorScheme.onPrimary),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .padding(start = 30.dp)
                    .fillMaxHeight()
                    .requiredWidth(30.dp)
                    .background(Color.Black)
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Box(modifier = Modifier.weight(0.1f)) {
                Box(
                    modifier = Modifier
                        .padding(end = 0.dp, top = 10.dp)
                        .clip(RoundedCornerShape(50))
                        .clickable {
                            onFavoriteClick()
                        }
                        .size(30.dp)
                        .border(0.5.dp, Color(0x11000000), RoundedCornerShape(50))
                        .padding(7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Rounded.Favorite, contentDescription = "Favorite")
                }
            }

        }
    }

}