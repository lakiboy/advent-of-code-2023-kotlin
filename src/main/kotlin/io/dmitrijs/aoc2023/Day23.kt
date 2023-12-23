package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP

class Day23(private val input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length
    private val start = Point(1, 0)
    private val finish = Point(maxX - 2, maxY - 1)

    fun puzzle1(): Int {
        val queue = ArrayDeque<Step>().apply { add(Step(start, start + UP, 0)) }
        var maxDist = -1

        while (queue.isNotEmpty()) {
            val (node, prev, dist) = queue.removeFirst()

            if (node == finish) {
                maxDist = maxOf(maxDist, dist)
                continue
            }

            val newDist = dist + 1
            val directions = when (node.value) {
                '>' -> listOf(RIGHT)
                '<' -> listOf(LEFT)
                '^' -> listOf(UP)
                'v' -> listOf(DOWN)
                else -> Direction.entries
            }

            directions
                .map { direction -> node + direction }
                .filter { neighbour -> neighbour != prev && neighbour.valid }
                .forEach { neighbour -> queue.add(Step(neighbour, node, newDist)) }
        }

        return maxDist
    }

    @Suppress("FunctionOnlyReturningConstant")
    fun puzzle2(): Int {
        return 0
    }

    private val Point.value get() = input[y][x]
    private val Point.valid get() = value != '#' // x in 0..<maxX && y in 0..<maxY

    private data class Step(val node: Point, val prev: Point, val dist: Int)
}
