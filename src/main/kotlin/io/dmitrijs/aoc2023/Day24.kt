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
        fun intersection(other: Stone): Pair<Double, Double>? {
            val a1 = vel.y.toDouble() / vel.x
            val b1 = pos.y.toDouble() - a1 * pos.x
            val a2 = other.vel.y.toDouble() / other.vel.x
            val b2 = other.pos.y.toDouble() - a2 * other.pos.x

            val x = (b2 - b1) / (a1 - a2)
            val y = x * a1 + b1

            return (x to y).takeIf { (x, _) -> (x > pos.x == vel.x > 0) && (x > other.pos.x == other.vel.x > 0) }
        }
    }

    private data class Point3d(val x: Long, val y: Long, val z: Long) {
        companion object {
            fun of(input: String) =
                input.split(',').map(String::trim).let { (x, y, z) -> Point3d(x.toLong(), y.toLong(), z.toLong()) }
        }
    }
}
