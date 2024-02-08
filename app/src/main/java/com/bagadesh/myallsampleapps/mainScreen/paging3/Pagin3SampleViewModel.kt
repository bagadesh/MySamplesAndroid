package com.bagadesh.myallsampleapps.mainScreen.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
class Pagin3SampleViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val base = MutableStateFlow("")

    val pagingDat1 = base.filter { it.isNotEmpty() }.flatMapLatest {
        getPagingFlow().map {
            it.map {
                UIData(CustomViewModel(it.id))
            }
        }
    }.cachedIn(viewModelScope)

    val pagingDat2 = getPagingFlow().map {
        it.map {
            UIData(CustomViewModel(it.id))
        }
    }.cachedIn(viewModelScope)

    val a = getPagingFlow()

    val loading = MutableStateFlow(false)

    init {
        refressh()
    }

    fun refressh() {
        loading.tryEmit(true)
        viewModelScope.launch {
            delay(500)
            base.tryEmit(Random.nextInt().toString())
            loading.tryEmit(false)
        }
    }

    fun refressh2() {
        loading.tryEmit(true)
        viewModelScope.launch {
            delay(500)
            pagingSource!!.invalidate()
            loading.tryEmit(false)
        }
    }

}

var pagingSource: SamplePagerSource? = null

data class RepoData(
    val id: String
)

fun getPagingFlow(): Flow<PagingData<RepoData>> {
    return Pager(
        PagingConfig(pageSize = 10, enablePlaceholders = false)
    ) {
        pagingSource = SamplePagerSource(20)
        pagingSource!!
    }.flow
}

class SamplePagerSource(val size: Int) : PagingSource<Int, RepoData>() {

    override fun getRefreshKey(state: PagingState<Int, RepoData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoData> {
        val key = params.key ?: 0
        return try {
            println("datmug, SamplePagerSource key = ${key}")
            delay(Random.nextLong(0, 500))
            val list = mutableListOf<RepoData>()
            repeat(1) {
                list.add(
                    RepoData(key.toString()+" -> $it")
                )
            }
            LoadResult.Page(
                data = list,
                prevKey = if (key > 0) key - 1 else null,
                nextKey = if (key < size - 1) key + 1 else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(Exception())
        }
    }
}