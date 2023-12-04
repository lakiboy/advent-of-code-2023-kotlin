package io.dmitrijs.aoc2023

import kotlin.math.pow

class Day04(private val input: List<String>) {
    fun puzzle1() = input.map { it.toPoints() }.sumOf { points ->
        if (points > 0) 2.toDouble().pow(points - 1).toLong() else 0L
    }

    fun puzzle2(): Long {
        val copies = MutableList(input.size) { 1L }

        return input.mapIndexed { index, line ->
            val r = index + 1..(index + line.toPoints()).coerceAtMost(input.size)
            for (i in r) copies[i] += copies[index]
            copies[index]
        }.sum()
    }

    private fun String.toNumbers() = Regex("\\d+").findAll(this).map { it.value }.toSet()

    private fun String.toPoints() = split(": ")[1].split(" | ").let { (win, all) ->
        (win.toNumbers() intersect all.toNumbers()).size
    }
}
