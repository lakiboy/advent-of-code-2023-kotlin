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
        val queue = PriorityQueue<Step>(compareByDescending { it.dist }).apply { add(Step(start, start + UP, 0)) }
        val cost = mutableMapOf<Point, Int>()
        var maxDist = -1

        while (queue.isNotEmpty()) {
            val (node, prev, dist) = queue.poll()

            if (node == finish) {
                maxDist = maxOf(maxDist, dist)
                continue
            }

            val newDist = dist + 1
            val directions = when(node.value) {
                '>' -> listOf(RIGHT)
                '<' -> listOf(LEFT)
                '^' -> listOf(UP)
                'v' -> listOf(DOWN)
                else -> Direction.entries
            }

            directions
                .map { direction -> node + direction }
                .filter { neighbour -> neighbour != prev && neighbour.valid }
                .forEach { neighbour ->
                    if (neighbour !in cost || newDist > cost.getValue(neighbour)) {
                        cost[neighbour] = newDist
                        queue.add(Step(node = neighbour, prev = node, dist = newDist))
                    }
                }
        }

        return maxDist
    }

//    data class Path(val point: Point, val num: Int, val distance: Int)

//    data class Path(val nodes: Set<Point>, val last: Point) {
//        constructor(node: Point) : this(mutableSetOf(node), node)
//
//        val size get() = nodes.size
//
//        fun add(node: Point): Path {
//            return copy(nodes = nodes + node, last = node)
//        }
//    }

    private data class Path(val node: Point, val branches: List<Int>)

//    fun puzzle2(): Int {
//        val queue = ArrayDeque<Pair<Path, Int>>().apply { add(Path(start + DOWN, 0) to 1) }
//        val visited = mutableSetOf(Path(start, 0))
//        var maxDist = -1
//
//        while (queue.isNotEmpty()) {
//            val (path, distance) = queue.removeFirst()
//            val (node, branch) = path
//
//            if (node == finish) {
//                maxDist = maxOf(maxDist, distance)
//                continue
//            }
//
//            Direction.entries
//                .map { direction -> node + direction }
//                .filter { neighbour ->
//                    (0..branch).all { Path(neighbour, it) !in visited } && neighbour.valid
//                }
//                .forEachIndexed { index, neighbour ->
//                    queue.add(Path(neighbour, branch + index) to distance + 1)
//                    visited.add(Path(neighbour, branch + index))
//                }
//        }
//
//        return maxDist
//    }

    fun puzzle2(): Int {
        val queue = PriorityQueue<Pair<Path, Int>>(compareByDescending { it.second }).apply { add(Path(start + DOWN, listOf(0)) to 1) }
        val visited = mutableSetOf(start to 0)
        val cost = mutableMapOf<Pair<Point, Int>, Int>()
        var maxDist = -1
        var branchInc = 0

        while (queue.isNotEmpty()) {
            val (path, distance) = queue.poll()
            val (node, branches) = path

            if (node == finish) {
                maxDist = maxOf(maxDist, distance)
                continue
            }

            val newCost = distance + 1

            Direction.entries
                .map { direction -> node + direction }
                .filter { neighbour ->
                    branches.all { (neighbour to it) !in visited } && neighbour.valid
                }
                .forEachIndexed { index, neighbour ->
                    val newBranches = if (index > 0) branches + listOf(++branchInc) else branches
                    val currentBranch = newBranches.last()

                    if ((neighbour to currentBranch) !in cost || newCost > cost.getValue(neighbour to currentBranch)) {
                        cost[neighbour to currentBranch] = newCost
                        queue.add(Path(neighbour, newBranches) to distance + 1)
                        visited.add(neighbour to newBranches.last())
                    }

//                    if (neighbour !in cost || newCost > cost.getValue(neighbour)) {
//                        cost[neighbour] = newCost
                        queue.add(Path(neighbour, newBranches) to distance + 1)
                        visited.add(neighbour to newBranches.last())
//                    }

//                    val newBranch = index + 1
//                    if (neighbour to newBranch !in cost || cost.getValue(neighbour to newBranch))
//
//                    queue.add(Path(neighbour, branch + index) to distance + 1)
//                    visited.add(Path(neighbour, branch + index))
                }
        }

        return maxDist
    }


//    fun puzzle2(): Int {
//        val queue = ArrayDeque<Path>().apply { add(Path(start)) }
//        var maxLen = 0
//
//        while (queue.isNotEmpty()) {
//            val path = queue.removeFirst()
//            val last = path.last
//
//            if (last == finish) {
//                maxLen = maxOf(maxLen, path.size)
//                continue
//            }
//
//            last.orthogonalNeighbours()
//                .filter { neighbour -> neighbour.valid && neighbour !in path.nodes }
//                .forEach { neighbour ->
//                    queue.add(path.add(neighbour))
//                }
//        }
//
//        return maxLen - 1
//
//        // return visited.groupBy { it.num }.maxOf { (_, list) -> list.size }
//    }

    private val Point.value get() = input[y][x]
    private val Point.valid get() = value != '#' // x in 0..<maxX && y in 0..<maxY

    private data class Step(val node: Point, val prev: Point, val dist: Int)
}
