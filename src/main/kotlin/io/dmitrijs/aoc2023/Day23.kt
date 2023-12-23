package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP
import java.util.PriorityQueue

class Day23(private val input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length
    private val start = Point(1, 0)
    private val finish = Point(maxX - 2, maxY - 1)

    fun puzzle1(): Int {
        val queue = PriorityQueue<Step>(compareByDescending { it.distance }).apply {
            add(Step(start, start + UP, DOWN, 0))
        }
        val cost = mutableMapOf<Pair<Point, Direction>, Int>()
        val slopes = emptySet<Char>() // setOf('>', '<', '^', 'v')
        var maxDist = -1

        while (queue.isNotEmpty()) {
            val (node, prev, _, distance) = queue.poll()

            if (node == finish) {
                maxDist = maxOf(maxDist, distance)
                continue
            }

            val newCost = distance + 1
            val directions = if (node.value in slopes) {
                when (node.value) {
                    '>' -> RIGHT
                    '<' -> LEFT
                    '^' -> UP
                    'v' -> DOWN
                    else -> error("Invalid slope '${node.value}'.")
                }.let { listOf(it) }
            } else {
                Direction.entries
            }

            directions
                .map { direction -> direction to node + direction }
                .filter { (_, neighbour) -> neighbour != prev && neighbour.valid }
                .forEach { (direction, neighbour) ->
                    if ((neighbour to direction) !in cost || newCost > cost.getValue(neighbour to direction)) {
                        cost[neighbour to direction] = newCost
                        queue.add(Step(neighbour, node, direction, newCost))
                    }
                }
        }

//        input.mapIndexed { y, line ->
//            line.mapIndexed { x, c -> if (Point(x, y) in cost) 'O' else c }.joinToString("")
//        }.joinToString("\n").also { println(it) }

        return maxDist
    }

    fun puzzle2(): Long {
        return 0
    }

    private val Point.value get() = input[y][x]
    private val Point.valid get() = value != '#' // x in 0..<maxX && y in 0..<maxY &&

    private data class Step(val point: Point, val prev: Point, val dir: Direction, val distance: Int)
}
