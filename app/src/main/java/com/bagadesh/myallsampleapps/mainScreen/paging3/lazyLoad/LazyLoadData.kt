package com.bagadesh.myallsampleapps.mainScreen.paging3.lazyLoad

import kotlinx.coroutines.flow.Flow

/**
 * Created by bagadesh on 01/12/22.
 */
data class LazyLoadData(
    val flow: Flow<LoadData>
)


sealed class LoadData {
    object Loading:LoadData()
    data class Loaded(val value: String): LoadData()
}