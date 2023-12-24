package io.dmitrijs.aoc2023

class Day24(input: List<String>) {
    private val lines = input.map { line ->
        val (pos, vel) = line.split(" @ ")
        LineCoefficients.of(Point3d.of(pos), Point3d.of(vel))
    }

    fun puzzle1(range: LongRange): Int {
        var result = 0

        for (i in 0..<lines.lastIndex) for (j in (i + 1)..<lines.size) {
            lines[i].intersection(lines[j])
                ?.also { println("Line $i / Line $j in $it") }
                ?.takeIf { it in range }
                ?.also { println("in") }
                ?.let { result++ }
        }

        return result
    }

    fun puzzle2(): Long {
        return 0L
    }

    private operator fun LongRange.contains(point: Pair<Double, Double>) =
        point.first >= start && point.first <= endInclusive && point.second >= start && point.second <= endInclusive

    private data class LineCoefficients(val a: Double, val b: Double, val c: Double) {
        fun intersection(other: LineCoefficients): Pair<Double, Double>? {
            val determinant = a * other.b - other.a * b

            // Lines are parallel.
            if (determinant == 0.0) return null

            val x = (other.b * c - b * other.c) / determinant
            val y = (a * other.c - other.a * c) / determinant

            return x to y
        }

        companion object {
            fun of(pos: Point3d, vel: Point3d): LineCoefficients {
                val (x1, y1) = pos
                val (x2, y2) = pos + vel

                val slope = (y2.toDouble() - y1) / (x2 - x1)

                // Calculate coefficients A and B for the line Ax + By = C
                val a = -slope
                val b = 1.0
                val c = slope * x1 - y1

                return LineCoefficients(a, b, c)
            }
        }
    }

    private data class Point3d(val x: Long, val y: Long, val z: Long) {
        operator fun plus(other: Point3d) = copy(x = x + other.x, y = y + other.y, z = z + other.z)

        companion object {
            fun of(input: String) =
                input.split(',').map(String::trim).let { (x, y, z) -> Point3d(x.toLong(), y.toLong(), z.toLong()) }
        }
    }
}
