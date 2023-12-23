package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP
import kotlin.math.abs

class Day10(private val input: List<String>, startDirection: Direction) {
    private val loop: List<Point>

    init {
        val start = input.indices.firstNotNullOf { y ->
            input[y].indices.firstNotNullOfOrNull { x ->
                Point(x, y).takeIf { it.value == 'S' }
            }
        }
        loop = getMainLoop(start, startDirection)
        assert(loop.size % 2 == 0) { "Loop length must be even." }
    }

    fun puzzle1() = loop.size / 2

    // Pick's theorem:
    //  * <area> = <inner points> + <outer points> / 2 - 1
    //  * <inner points> = <area> + 1 - <outer points> / 2
    //  * <outer points> = loop's length
    //  * <area> = calculated with Gauss's area formula
    fun puzzle2() = gaussArea(loop) + 1 - loop.size / 2

    // Returns correct answer
    fun puzzle2InputHack(): Int {
        val min = input.size / 4
        val max = min + min * 2 - 1

        return input.indices.sumOf { y ->
            input[y].indices.count { x ->
                x in min..max && y in min..max && Point(x, y) !in loop
            }
        }
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

    private fun gaussArea(points: List<Point>): Int {
        val last = points.lastIndex
        val area = (0..<last).fold(0) { acc, i ->
            acc + points[i].x * points[i + 1].y - points[i + 1].x * points[i].y
        } + points[last].x * points[0].y - points[0].x * points[last].y

        return abs(area) / 2
    }

    private data class Pipe(private val char: Char) {
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
