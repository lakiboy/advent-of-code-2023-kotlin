package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP

private typealias DirectedPoint = Pair<Point, Direction>

class Day16(private val input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length

    fun puzzle1() = getBeamVisits(Point(0, 0) to RIGHT)

    // Brute force solution.
    fun puzzle2() = listOf(
        (0..<maxX).maxOf { x -> getBeamVisits(Point(x, 0) to DOWN) },
        (0..<maxX).maxOf { x -> getBeamVisits(Point(x, maxY - 1) to UP) },
        (0..<maxY).maxOf { y -> getBeamVisits(Point(0, y) to LEFT) },
        (0..<maxY).maxOf { y -> getBeamVisits(Point(maxX - 1, y) to RIGHT) },
    ).max()

    @Suppress("CyclomaticComplexMethod")
    private fun getBeamVisits(start: DirectedPoint): Int {
        val queue = ArrayDeque<DirectedPoint>().apply { add(start) }
        val visited = hashSetOf<DirectedPoint>()

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()

            if (!node.valid || node in visited) continue

            when (node.state) {
                '|' to RIGHT, '|' to LEFT -> listOf(UP, DOWN)
                '-' to UP, '-' to DOWN -> listOf(LEFT, RIGHT)
                '/' to RIGHT -> listOf(UP)
                '/' to LEFT -> listOf(DOWN)
                '/' to UP -> listOf(RIGHT)
                '/' to DOWN -> listOf(LEFT)
                '\\' to RIGHT -> listOf(DOWN)
                '\\' to LEFT -> listOf(UP)
                '\\' to UP -> listOf(LEFT)
                '\\' to DOWN -> listOf(RIGHT)
                else -> listOf(node.second)
            }.forEach { dir ->
                queue.add(node + dir)
            }.also {
                visited.add(node)
            }
        }

        return visited.distinctBy { it.first }.size
    }

    private val DirectedPoint.state get() = value to second
    private val DirectedPoint.value get() = input[first.y][first.x]
    private val DirectedPoint.valid get() = first.x in 0..<maxX && first.y in 0..<maxY

    private operator fun DirectedPoint.plus(direction: Direction) = (first + direction) to direction
}
