package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP
import java.util.PriorityQueue

class Day17(private val input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length

    fun puzzle1() = solve(minRepeat = 1, maxRepeat = 3)

    fun puzzle2() = solve(minRepeat = 4, maxRepeat = 10)

    private fun solve(minRepeat: Int, maxRepeat: Int): Int {
        val start = Point(0, 0)
        val finish = Point(maxX - 1, maxY - 1)

        val queue = PriorityQueue<Block>(compareBy { it.distance }).apply {
            add(Block(start, DirectedLine(RIGHT, 1), 0))
            add(Block(start, DirectedLine(DOWN, 1), 0))
        }
        val visited = hashSetOf<Pair<Point, DirectedLine>>()

        while (queue.isNotEmpty()) {
            val (node, line, distance) = queue.poll()

            if (node == finish && line.repeat >= minRepeat) return distance

            line
                .candidates(minRepeat, maxRepeat)
                .map { direction -> (node + direction) to direction }
                .filter { (neighbour, _) -> neighbour.valid }
                .filter { it !in visited }
                .forEach { (neighbour, nextLine) ->
                    visited.add(neighbour to nextLine)
                    queue.add(Block(neighbour, nextLine, distance + neighbour.weight))
                }
        }

        return -1
    }

    private val Point.weight get() = input[y][x].digitToInt()
    private val Point.valid get() = x in 0..<maxX && y in 0..<maxY

    private operator fun Point.plus(line: DirectedLine) = this + line.direction

    private data class DirectedLine(val direction: Direction, val repeat: Int = 1) {
        fun candidates(minRepeat: Int, maxRepeat: Int) = buildList {
            if (repeat < maxRepeat) add(copy(repeat = repeat + 1))
            if (repeat >= minRepeat) {
                add(toLeft())
                add(toRight())
            }
        }

        private fun DirectedLine.toRight() = when (direction) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }.let { DirectedLine(it) }

        private fun DirectedLine.toLeft() = when (direction) {
            UP -> LEFT
            LEFT -> DOWN
            DOWN -> RIGHT
            RIGHT -> UP
        }.let { DirectedLine(it) }
    }

    private data class Block(val point: Point, val line: DirectedLine, val distance: Int)
}
