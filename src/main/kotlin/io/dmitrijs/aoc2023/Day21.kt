package io.dmitrijs.aoc2023

class Day21(private val input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length
    private val start = input.indices.firstNotNullOf { y ->
        input[y].indices.firstNotNullOfOrNull { x ->
            Point(x, y).takeIf { input[y][x] == 'S' }
        }
    }

    fun puzzle1(steps: Int) = solve(steps)

    fun puzzle2(steps: Int) = solve(steps, infinite = true)

    private fun solve(steps: Int, infinite: Boolean = false): Int {
        val queue = ArrayDeque<Pair<Point, Int>>().apply { add(start to 0) }
        val visited = hashMapOf<Point, Int>()
        val oddOrEven = steps % 2

        while (queue.isNotEmpty()) {
            val (node, step) = queue.removeFirst()

            if (step >= steps) {
                continue
            }

            val nextStep = step + 1

            node.orthogonalNeighbours()
                .filter { it.valid(infinite) && it !in visited }
                .forEach { neighbour ->
                    visited[neighbour] = nextStep
                    queue.add(neighbour to nextStep)
                }
        }

        return visited.filterValues { it % 2 == oddOrEven }.size
    }

    private fun Point.valid(infinite: Boolean): Boolean {
        val (newX, newY) = if (infinite) {
            val rx = x % maxX
            val ry = y % maxY
            (if (rx < 0) rx + maxX else rx) to (if (ry < 0) ry + maxY else ry)
        } else {
            x to y
        }

        return newX in 0..<maxX && newY in 0..<maxY && input[newX][newY] != '#'
    }
}
