package com.bagadesh.myallsampleapps.others

import java.util.Stack

/**
 * Created by bagadesh on 09/01/23.
 */

fun main() {
//    val nums = IntArray(5) { it }
//    nums.indexOfFirst { it != 0 }
    val s = "10000000"
    println(s.trimStart('0'))
    val stac = Stack<Int>()
    stac.addAll(stac)
}

val comparator = object: Comparator<Int> {
    override fun compare(a: Int, b: Int): Int {
        val t1 = a.toString()+b.toString()
        val t2 = b.toString()+a.toString()
        return when {
            t1 == t2 -> 0
            t1 > t2 -> 1
            else -> -1
        }
    }
}
