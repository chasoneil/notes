package com.chason.documents.note

/**
 * The code is for studying kotlin
 * This is items of this class note
 * 1. String
 *
 */
fun main () {

    // Strings are sequence of character
    val str = "abcd 123"

    // we car use string[i] to get character
    for (s in str) {
        println(s)
    }

    val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    println(text)



}
