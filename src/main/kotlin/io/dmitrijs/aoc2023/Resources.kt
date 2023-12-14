package io.dmitrijs.aoc2023

import java.io.File
import java.net.URI

internal object Resources {
    fun resourceAsText(fileName: String): String = File(fileName.toURI()).readText()

    fun resourceAsLines(fileName: String): List<String> = File(fileName.toURI()).readLines()

    private fun String.toURI(): URI =
        Resources.javaClass.getResource("/$this.txt")
            ?.toURI()
            ?: error("Cannot find resource: $this")
}
