package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP

class Day14(input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length
    private val deck = Array(maxY) { y -> input[y].toCharArray() }

    fun puzzle1() = tiltUp().let { deck.score }

    fun puzzle2(): Int {
        var step = 0
        var skip = true
        val seen = hashMapOf<String, Int>()

        while (step < 1_000_000_000) {
            tiltUp()
            tiltLeft()
            tiltDown()
            tiltRight()

            if (skip) {
                when (val key = deck.asString()) {
                    in seen -> {
                        val cycle = step - seen.getValue(key)
                        val cyclesLeft = (1_000_000_000 - step) / cycle
                        step += cycle * cyclesLeft
                        skip = false
                    }
                    else -> seen[key] = step
                }
            }

            step++
        }

        return deck.score
    }

    private fun tiltUp() {
        for (y in 0..<maxY)
            for (x in 0..<maxX)
                tilt(x, y, UP)
    }

    private fun tiltDown() {
        for (y in (maxY - 1) downTo 0)
            for (x in 0..<maxX)
                tilt(x, y, DOWN)
    }

    private fun tiltLeft() {
        for (x in 0..<maxX)
            for (y in 0..<maxY)
                tilt(x, y, LEFT)
    }

    private fun tiltRight() {
        for (x in (maxX - 1) downTo 0)
            for (y in 0..<maxY)
                tilt(x, y, RIGHT)
    }

    private fun tilt(x: Int, y: Int, direction: Direction) {
        var p = Point(x, y)
        var n = p + direction
        while (p.value == 'O' && n.valid && n.value == '.') {
            p.value = '.'
            n.value = 'O'
            p = n
            n = p + direction
        }
    }

    private fun Array<CharArray>.asString() = joinToString("\n") { it.joinToString("") }

    private val Array<CharArray>.score get() = indices.fold(0) { acc, y ->
        acc + deck[y].count { it == 'O' } * (maxY - y)
    }

    private val Point.valid get() = x in 0..<maxX && y in 0..<maxY

    private var Point.value
        get() = deck[y][x]
        set(value) {
            deck[y][x] = value
        }
}
