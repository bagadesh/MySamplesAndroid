package com.bagadesh.featureflags.logger

/**
 * Created by bagadesh on 15/04/23.
 */
interface Logger {

    fun debug(message: String, throwable: Throwable? = null)

    fun error(message: String, throwable: Throwable? = null)

}