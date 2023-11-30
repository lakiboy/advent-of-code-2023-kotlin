package io.dmitrijs.aoc2023

class Day00(private val input: List<Int>) {
    fun puzzle1(): Int {
        return input.sum()
    }

    fun puzzle2(): Int {
        return input.max()
    }
}
