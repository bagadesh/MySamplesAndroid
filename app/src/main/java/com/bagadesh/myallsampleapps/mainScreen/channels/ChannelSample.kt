package com.bagadesh.myallsampleapps.mainScreen.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import timber.log.Timber

/**
 * Created by bagadesh on 27/12/23.
 */
class ChannelSample {
    val scope = CoroutineScope(Dispatchers.Main)
    val channel = Channel<Int>(onBufferOverflow = BufferOverflow.SUSPEND, capacity = 1)
    var count = 0
    var sendCount =0

    suspend fun receive(index: Int = count++) {
        Timber.d("receive starting index = $index")
        val received = channel.receive()
        Timber.d("recieve ended $received index = $index")
    }

    suspend fun send() {
        val local = sendCount++
        Timber.d("send starting $local")
        channel.send(local)
        Timber.d("send ended $local")
    }
}