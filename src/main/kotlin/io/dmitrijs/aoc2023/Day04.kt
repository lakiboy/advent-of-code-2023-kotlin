package io.dmitrijs.aoc2023

class Day04(private val input: List<String>) {
    fun puzzle1() = input.map { it.toPoints() }.sumOf { points ->
        if (points > 0) 1 shl (points - 1) else 0
    }

    fun puzzle2(): Int {
        val copies = MutableList(input.size) { 1 }

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
