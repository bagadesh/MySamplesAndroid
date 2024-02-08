package com.bagadesh.myallsampleapps.mainScreen.cache

import android.util.LruCache
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by bagadesh on 13/12/22.
 */


object CacheProcessData {

    private const val cacheSize = 4 * 1024 * 1024; // 4MiB
    val cache = object : LruCache<String, Any>(cacheSize) {

    }

}


abstract class CacheProcessor<T> {


    protected abstract suspend fun getData(): T

    private var job: Job? = null

    private var isStale = false

    private var cachedData: T? = null

    private val key: String by lazy {
        hashCode().toString()
    }

    suspend fun initialize() {
        if (job != null) {
            throw IllegalStateException("CacheProcessor cannot be initialize again")
        }
        coroutineScope {
            job = launch {
                val data = getData()
                CacheProcessData.cache.put(key, data)
            }
        }
    }

    suspend fun getCachedData(): T {
        return if (isStale) {
            getData()
        } else {
            job?.join()
            val data = CacheProcessData.cache.remove(hashCode().toString()) as? T
            isStale = true
            data ?: getData()
        }
    }

}

class CustomCacheProcessor constructor(
    private val dataSource: DataSource //REMOTE
) : CacheProcessor<String>() {

    override suspend fun getData(): String {
        return dataSource.getItem()
    }
}

class CachedDataSource constructor(
    private val itemCacheProcessor: CustomCacheProcessor,
) : DataSource {

    override suspend fun getItem(): String {
        return itemCacheProcessor.getCachedData()
    }
}

interface DataSource {

    suspend fun getItem(): String

}

class RemoteDataSource : DataSource {

    override suspend fun getItem(): String {
        delay(2000)
        return "remoteData"
    }
}