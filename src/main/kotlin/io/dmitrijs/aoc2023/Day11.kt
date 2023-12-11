package io.dmitrijs.aoc2023

class Day11(private val input: List<String>) {
    private val points = input.indices.flatMap { y ->
        input[y].indices.mapNotNull { x ->
            Point(x, y).takeIf { it.exists }
        }
    }.toSet()

    private val emptyX = input.first().indices.mapNotNull { x ->
        if (input.indices.none { y -> Point(x, y).exists }) x else null
    }.toSet()

    private val emptyY = input.indices.mapNotNull { y ->
        if (input[y].indices.none { x -> Point(x, y).exists }) y else null
    }.toSet()

    fun puzzle1() = solve(2)

    fun puzzle2(scale: Int) = solve(scale)

    private fun solve(scale: Int): Long {
        var total = 0L
        val pairs = hashSetOf<Pair<Point, Point>>()

        for (p1 in points) for (p2 in points) {
            if (p1 == p2 || (p1 to p2) in pairs) {
                continue
            }

            pairs.add(p1 to p2)
            pairs.add(p2 to p1)

            val xScale = when {
                p1.x < p2.x -> (p1.x..p2.x).count { it in emptyX }
                else -> (p2.x..p1.x).count { it in emptyX }
            }
            val yScale = when {
                p1.y < p2.y -> (p1.y..p2.y).count { it in emptyY }
                else -> (p2.y..p1.y).count { it in emptyY }
            }

            total += p1.distanceTo(p2) + xScale * (scale - 1) + yScale * (scale - 1)
        }

        return total
    }

    private val Point.exists get() = input[y][x] == '#'
}
