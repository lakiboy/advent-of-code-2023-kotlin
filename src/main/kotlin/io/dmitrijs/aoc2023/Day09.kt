package io.dmitrijs.aoc2023

class Day09(input: List<String>) {
    private val lines = input.map { it.split(' ').map(String::toLong) }

    fun puzzle1() = lines.sumOf { numbers ->
        numbers.firstLastHistory().fold(0L) { acc, pair -> acc + pair.second }
    }

    fun puzzle2() = lines.sumOf { numbers ->
        numbers.firstLastHistory().fold(0L) { acc, pair -> pair.first - acc }
    }

    private fun List<Long>.firstLastHistory(): List<Pair<Long, Long>> {
        val result = mutableListOf<Pair<Long, Long>>()
        var current = this

        do {
            result.add(current.first() to current.last())
            current = current.zipWithNext().map { (a, b) -> b - a }
        } while (current.any { it != 0L })

        return result.reversed()
    }
}
