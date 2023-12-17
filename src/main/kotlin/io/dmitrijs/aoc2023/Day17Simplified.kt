package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP
import java.util.PriorityQueue

class Day17Simplified(private val input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length
    private val finish = Point(maxX - 1, maxY - 1)

    fun puzzle1() = solve(minRepeat = 1, maxRepeat = 3)

    fun puzzle2() = solve(minRepeat = 4, maxRepeat = 10)

    private fun solve(minRepeat: Int, maxRepeat: Int): Int {
        val queue = PriorityQueue<Block>(compareBy { it.distance }).apply {
            add(Block(Point(1, 0), RIGHT, 1, 0))
            add(Block(Point(0, 1), DOWN, 1, 0))
        }
        val visits = hashMapOf<Triple<Point, Direction, Int>, Int>()

        while (queue.isNotEmpty()) {
            val (node, direction, repeat, distance) = queue.poll()

            val newDistance = distance + node.weight
            if (node == finish && repeat >= minRepeat) {
                return newDistance
            }

            val visit = Triple(node, direction, repeat)
            if (newDistance >= visits.getOrDefault(visit, Int.MAX_VALUE)) {
                continue
            }

            visits[visit] = newDistance

            if (repeat >= minRepeat) {
                val l = direction.toLeft()
                val r = direction.toRight()
                (node + l).takeIf { it.valid }?.let { queue.add(Block(it, l, 1, newDistance)) }
                (node + r).takeIf { it.valid }?.let { queue.add(Block(it, r, 1, newDistance)) }
            }

            if (repeat < maxRepeat) {
                (node + direction).takeIf { it.valid }?.let { queue.add(Block(it, direction, repeat + 1, newDistance)) }
            }
        }

        return -1
    }

    private val Point.weight get() = input[y][x].digitToInt()
    private val Point.valid get() = x in 0..<maxX && y in 0..<maxY

    private fun Direction.toRight() = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }

    private fun Direction.toLeft() = when (this) {
        UP -> LEFT
        LEFT -> DOWN
        DOWN -> RIGHT
        RIGHT -> UP
    }

    private data class Block(
        val point: Point,
        val direction: Direction,
        val repeat: Int,
        val distance: Int,
    )
}
