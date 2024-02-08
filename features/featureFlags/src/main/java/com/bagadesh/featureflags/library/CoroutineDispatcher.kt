package com.bagadesh.featureflags.library

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Created by bagadesh on 15/04/23.
 */
interface WrapDispatcher {

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher

    val scope: CoroutineScope

}


fun WrapDispatcher.ioLaunch(action: suspend CoroutineScope.() -> Unit) {
    scope.launch(context = io, block = action)
}

class WrapDispatcherImpl : WrapDispatcher {

    private val myScope = CoroutineScope(main + SupervisorJob())

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val scope: CoroutineScope
        get() = myScope
}