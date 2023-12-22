package io.dmitrijs.aoc2023

class Day21(private val input: List<String>) {
    private val maxY = input.size
    private val maxX = input.first().length
    private val start = input.indices.firstNotNullOf { y ->
        input[y].indices.firstNotNullOfOrNull { x ->
            Point(x, y).takeIf { input[y][x] == 'S' }
        }
    }

    fun puzzle1(steps: Int) = visitedPointsMap().filterValues { steps >= it && it % 2 == 0 }.size

    /**
     * Solved by algorithm found on Reddit.
     */
    fun puzzle2(steps: Int): Long {
        val points = visitedPointsMap()

        // 26501365 = 65 + (202300 * 131)
        //  > 131 - board side;
        //  > 65 - steps till the edge from starting position;
        //  > 202300 - additional boards in each direction.
        val n = ((steps - (input.size / 2)) / input.size).toLong()
        require(n == 202_300L)

        val oddFull = points.filterValues { it % 2 == 1 }.size
        val oddCorners = points.filterValues { it > 65 && it % 2 == 1 }.size

        val evenFull = points.filterValues { it % 2 == 0 }.size
        val evenCorners = points.filterValues { it > 65 && it % 2 == 0 }.size

        return (n + 1) * (n + 1) * oddFull + n * n * evenFull - ((n + 1) * oddCorners) + (n * evenCorners)
    }

    private fun visitedPointsMap(): Map<Point, Int> {
        val queue = ArrayDeque<Pair<Point, Int>>().apply { add(start to 0) }
        val visited = hashMapOf<Point, Int>()

        while (queue.isNotEmpty()) {
            val (point, step) = queue.removeFirst()

            val nextStep = step + 1

            point.orthogonalNeighbours()
                .filter { it.valid && it !in visited }
                .forEach { neighbour ->
                    visited[neighbour] = nextStep
                    queue.add(neighbour to nextStep)
                }
        }

        return visited
    }

    private val Point.valid get() = x in 0..<maxX && y in 0..<maxY && input[x][y] != '#'
}
