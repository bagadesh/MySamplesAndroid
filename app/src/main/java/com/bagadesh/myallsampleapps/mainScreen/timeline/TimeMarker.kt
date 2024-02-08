package com.bagadesh.myallsampleapps.mainScreen.timeline

import kotlin.math.log

/**
 * Created by bagadesh on 24/01/23.
 */
interface TimeMarker {

    fun mark(tag: String)

    fun getTimeLine(): TimeLineResult
}

data class TimeLineResult(
    val mainTag: String,
    val timelines: List<TimeLine>
)

data class TimeLine(
    val tag: String,
    val loggedTime: Long,
    val loggedTimeBasedOnTime: Long,
    val loggedTimeFromStart: Long,
)

val splashTracker by lazy { getTimeMarker("SplashTracker") }

fun getTimeMarker(tag: String): TimeMarker {
    return TimeMarkerImpl(tag)
}

class TimeMarkerImpl(private val tag: String) : TimeMarker {

    private val marks = mutableListOf<Pair<Long, String>>()

    override fun mark(tag: String) {
        marks.add(System.currentTimeMillis() to tag)
    }

    override fun getTimeLine(): TimeLineResult {
        var previous: Long = marks.first().first
        val first: Long = marks.first().first
        return TimeLineResult(
            mainTag = tag,
            timelines = marks.map {
                TimeLine(
                    tag = it.second,
                    loggedTime = it.first,
                    loggedTimeBasedOnTime = it.first - previous,
                    loggedTimeFromStart = it.first - first
                ).also { _ ->
                    previous = it.first
                }
            }
        )
    }
}