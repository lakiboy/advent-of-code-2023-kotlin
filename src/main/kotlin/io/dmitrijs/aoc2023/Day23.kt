package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Direction.DOWN
import io.dmitrijs.aoc2023.Direction.LEFT
import io.dmitrijs.aoc2023.Direction.RIGHT
import io.dmitrijs.aoc2023.Direction.UP
import kotlin.math.max

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
                maxDist = max(maxDist, dist)
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
                .filter { neighbour -> neighbour != prev && neighbour.space }
                .forEach { neighbour -> queue.add(Step(neighbour, node, newDist)) }
        }

        return maxDist
    }

    fun puzzle2(): Int {
        val graph = getJunctionGraph()
        val queue = ArrayDeque<Pair<Set<Point>, Int>>().apply { add(setOf(start) to 0) }
        var maxDist = -1

        while (queue.isNotEmpty()) {
            val (path, dist) = queue.removeLast()
            val node = path.last()

            if (node == finish) {
                maxDist = max(maxDist, dist)
                continue
            }

            graph
                .filter { (from, till) -> node == from && till !in path }
                .forEach { (_, till, d) ->
                    queue.add(path.toMutableSet().apply { add(till) } to dist + d)
                }
        }

        return maxDist
    }

    private fun getJunctionGraph(): List<Edge> {
        val junctions = input.flatMapIndexed { y, line ->
            line.indices.mapNotNull { x ->
                Point(x, y).takeIf { p ->
                    p.orthogonalNeighbours().count { it.valid && it.value in "><^v" } > 2
                }
            }
        }

        val nodes = listOf(start) + junctions + listOf(finish)
        val edges = mutableListOf<Edge>()
        val heads = mutableListOf(start)

        val visited = mutableSetOf<Point>()

        while (heads.isNotEmpty()) {
            val head = heads.removeFirst()
            if (head == finish) break

            val queue = ArrayDeque<Pair<Point, Int>>().apply { add(head to 0) }
            visited.add(head)

            while (queue.isNotEmpty()) {
                val (node, dist) = queue.removeFirst()

                if (head != node && node in nodes) {
                    heads.add(node)
                    visited.remove(node)
                    edges.add(Edge(head, node, dist))
                    edges.add(Edge(node, head, dist))
                    continue
                }

                node.orthogonalNeighbours()
                    .filter { it.valid && it.space && it !in visited }
                    .forEach { neighbour ->
                        visited.add(neighbour)
                        queue.add(neighbour to dist + 1)
                    }
            }
        }

        return edges
    }

    private val Point.value get() = input[y][x]
    private val Point.space get() = value != '#'
    private val Point.valid get() = x in 0..<maxX && y in 0..<maxY

    private data class Step(val node: Point, val prev: Point, val dist: Int)

    private data class Edge(val from: Point, val till: Point, val dist: Int)
}
