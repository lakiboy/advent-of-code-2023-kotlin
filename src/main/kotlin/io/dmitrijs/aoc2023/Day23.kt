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
        val queue = ArrayDeque<Edge>().apply { add(Edge(start + UP, start, 0)) }
        var maxDist = -1

        while (queue.isNotEmpty()) {
            val (prev, node, dist) = queue.removeFirst()

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
                .forEach { neighbour -> queue.add(Edge(node, neighbour, newDist)) }
        }

        return maxDist
    }

    // ~12s
    fun puzzle2(): Int {
        val (nodes, graph) = getJunctionGraph()
        val queue = ArrayDeque<Triple<Point, Long, Int>>().apply { add(Triple(start, 1L, 0)) }
        var maxDist = -1

        // Using mask as path doubles the performance.
        val masks = nodes.withIndex().associate { (index, node) -> node to (1L shl index) }

        while (queue.isNotEmpty()) {
            val (node, path, dist) = queue.removeFirst()

            if (node == finish) {
                maxDist = max(maxDist, dist)
                continue
            }

            graph
                .filter { (from, till) -> node == from && (masks.getValue(till) and path) == 0L }
                .forEach { (_, till, d) ->
                    queue.add(Triple(till, path or masks.getValue(till), dist + d))
                }
        }

        return maxDist
    }

    private fun getJunctionGraph(): Pair<List<Point>, List<Edge>> {
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
        val queue = ArrayDeque<Pair<Point, Int>>()

        val visited = mutableSetOf<Point>()

        while (heads.isNotEmpty()) {
            val head = heads.removeFirst()
            if (head == finish) break

            queue.add(head to 0)
            visited.add(head)

            while (queue.isNotEmpty()) {
                val (tail, dist) = queue.removeFirst()

                if (head != tail && tail in nodes) {
                    heads.add(tail)
                    visited.remove(tail)
                    edges.add(Edge(head, tail, dist))
                    edges.add(Edge(tail, head, dist))
                    continue
                }

                tail.orthogonalNeighbours()
                    .filter { it.valid && it.space && it !in visited }
                    .forEach { neighbour ->
                        visited.add(neighbour)
                        queue.add(neighbour to dist + 1)
                    }
            }
        }

        return nodes to edges
    }

    private val Point.value get() = input[y][x]
    private val Point.space get() = value != '#'
    private val Point.valid get() = x in 0..<maxX && y in 0..<maxY

    private data class Edge(val from: Point, val till: Point, val dist: Int)
}
