package com.bagadesh.featureflags.logger.impl

import com.bagadesh.featureflags.logger.Logger
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bagadesh on 15/04/23.
 */

private const val TAG = "FFMainTag"

class LoggerImpl @Inject constructor(): Logger {

    init {
        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun debug(message: String, throwable: Throwable?) {
        Timber.tag(TAG).d(t = throwable, message = message)
    }

    override fun error(message: String, throwable: Throwable?) {
        Timber.tag(TAG).e(t = throwable, message = message)
    }
}