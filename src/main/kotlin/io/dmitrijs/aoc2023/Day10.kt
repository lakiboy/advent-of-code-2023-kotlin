package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP

class Day10(private val input: List<String>, startDirection: Direction) {
    private val loop: List<Point>

    init {
        var start = Point(0, 0)
        input.indices.forEach { y ->
            input[y].indices.forEach { x ->
                val p = Point(x, y)
                if (p.value == 'S') start = p
            }
        }
        loop = getMainLoop(start, startDirection)
    }

    fun puzzle1() = if (loop.size % 2 == 1) loop.size / 2 + 1 else loop.size / 2

    @Suppress("FunctionOnlyReturningConstant")
    fun puzzle2(): Int {
        return 0
    }

    private val Point.value get() = input[y][x]

    private fun Point.turn(direction: Direction) = Pipe(value).turn(direction)

    private fun getMainLoop(start: Point, startDirection: Direction): List<Point> {
        val loop = mutableListOf<Point>()
        var node = start
        var direction = startDirection

        do {
            loop.add(node)
            node += direction
            if (node != start) direction = node.turn(direction)
        } while (node != start)

        return loop
    }

    private data class Pipe(private val char: Char) {
        @Suppress("CyclomaticComplexMethod")
        fun turn(direction: Direction): Direction = when (direction) {
            RIGHT -> when (char) {
                '7' -> DOWN
                'J' -> UP
                '-' -> RIGHT
                else -> error("Invalid $direction flow into $char")
            }
            LEFT -> when (char) {
                'F' -> DOWN
                'L' -> UP
                '-' -> LEFT
                else -> error("Invalid $direction flow into $char")
            }
            UP -> when (char) {
                'F' -> RIGHT
                '7' -> LEFT
                '|' -> UP
                else -> error("Invalid $direction flow into $char")
            }
            DOWN -> when (char) {
                'J' -> LEFT
                'L' -> RIGHT
                '|' -> DOWN
                else -> error("Invalid $direction flow into $char")
            }
        }
    }
}
