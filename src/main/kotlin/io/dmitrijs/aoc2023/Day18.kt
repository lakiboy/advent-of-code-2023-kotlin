package io.dmitrijs.aoc2023

import kotlin.math.abs

class Day18(private val input: List<String>) {
    fun puzzle1() = calculateArea { line ->
        line.split(' ').let { parts ->
            directionsMap.getValue(parts[0].first()) to parts[1].toInt()
        }
    }

    fun puzzle2() = calculateArea { line ->
        colorRegex.find(line)
            ?.let { it.groupValues[2].toInt() to it.groupValues[1].hexToInt() }
            ?: error("Invalid line $line supplied.")
    }

    private fun calculateArea(parser: (String) -> Pair<Int, Int>): Long {
        var x = 0
        var y = 0
        var len = 0
        var point = Point(x, y)
        val points = mutableListOf<Point>()

        input.forEach { line ->
            val (dir, count) = parser(line)
            val (dx, dy) = directions[dir]

            x += dx * count
            y += dy * count
            len += point.distanceTo(Point(x, y))

            point = Point(x, y)
            points.add(point)
        }

        // Pick's theorem - see Day10 for more information.
        val innerArea = gaussArea(points) + 1 - len / 2

        return len + innerArea
    }

    private fun gaussArea(pp: List<Point>): Long {
        val last = pp.lastIndex
        val area = (0..<last).fold(0L) { acc, i ->
            acc + pp[i].x.toLong() * pp[i + 1].y - pp[i + 1].x.toLong() * pp[i].y
        } + pp[last].x.toLong() * pp[0].y - pp[0].x.toLong() * pp[last].y

        return abs(area) / 2
    }

    companion object {
        private val colorRegex = Regex("#([0-9a-f]{5})(\\d)")
        private val directions = arrayOf(
            (1 to 0),  // R
            (0 to 1),  // D
            (-1 to 0), // L
            (0 to -1), // U
        )
        private val directionsMap = hashMapOf('R' to 0, 'D' to 1, 'L' to 2, 'U' to 3)
    }
}
