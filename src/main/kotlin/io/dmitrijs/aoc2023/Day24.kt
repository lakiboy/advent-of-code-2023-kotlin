package io.dmitrijs.aoc2023

class Day24(input: List<String>) {
    private val stones = input.map { line ->
        val (pos, vel) = line.split(" @ ")
        Stone(Point3d.of(pos), Point3d.of(vel))
    }

    fun puzzle1(range: LongRange): Int {
        var result = 0

        for (i in 0..<stones.lastIndex) for (j in (i + 1)..<stones.size) {
            val s1 = stones[i]
            val s2 = stones[j]
            s1.intersection(s2)?.takeIf { it in range }?.let { result++ }
        }

        return result
    }

    @Suppress("FunctionOnlyReturningConstant")
    fun puzzle2(): Long {
        return 0L
    }

    private operator fun LongRange.contains(point: Pair<Double, Double>) = with(point) {
        first >= start && first <= endInclusive && second >= start && second <= endInclusive
    }

    private data class Stone(val pos: Point3d, val vel: Point3d) {
        // (Px + t * Vy; Py + t * Vy) -> ax + by = c
        // Parametric form -> standard form, where:
        //   a = Vy
        //   b = -Vx
        //   c = Vy * x - Vx * y
        private val line = Line(
            a = vel.y.toDouble(),
            b = -vel.x.toDouble(),
            c = pos.x.toDouble() * vel.y - pos.y.toDouble() * vel.x
        )

        fun intersection(other: Stone): Pair<Double, Double>? {
            if (line.parallelTo(other.line)) return null

            val (a1, b1, c1) = line
            val (a2, b2, c2) = other.line

            val x = (c1 * b2 - c2 * b1) / (a1 * b2 - a2 * b1)
            val y = (c2 * a1 - c1 * a2) / (a1 * b2 - a2 * b1)

            // (x - Px) same sign as Vx
            // (y - Py) same sign as Vy
            return (x to y)
                .takeIf { (x, _) -> (x > pos.x == vel.x > 0) && (x > other.pos.x == other.vel.x > 0) }
                ?.takeIf { (_, y) -> (y > pos.y == vel.y > 0) && (y > other.pos.y == other.vel.y > 0) }
        }

        private data class Line(val a: Double, val b: Double, val c: Double) {
            fun parallelTo(other: Line) = a * other.b == other.a * b
        }
    }

    private data class Point3d(val x: Long, val y: Long, val z: Long) {
        companion object {
            fun of(input: String) =
                input.split(',').map(String::trim).let { (x, y, z) -> Point3d(x.toLong(), y.toLong(), z.toLong()) }
        }
    }
}
