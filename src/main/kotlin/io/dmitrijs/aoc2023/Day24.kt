package io.dmitrijs.aoc2023

import kotlin.math.sign

class Day24(input: List<String>) {
    private val stones = input.map { line ->
        val (pos, vel) = line.split(" @ ")
        Stone(Point3d.of(pos), Point3d.of(vel))
    }

    // if i128::signum(x - hailstones[i].pos.0) != i128::signum(hailstones[i].vel.0) {
    //     // println!("Hailstones' path will cross in the past");
    //     continue;
    // }
    // if i128::signum(x - hailstones[j].pos.0) != i128::signum(hailstones[j].vel.0) {
    //     // println!("Hailstones' path will cross in the past");
    //     continue;
    // }
    // if i128::signum(y - hailstones[i].pos.1) != i128::signum(hailstones[i].vel.1) {
    //     // println!("Hailstones' path will cross in the past");
    //     continue;
    // }
    // if i128::signum(y - hailstones[j].pos.1) != i128::signum(hailstones[j].vel.1) {
    //     // println!("Hailstones' path will cross in the past");
    //     continue;
    // }

    fun puzzle1(range: LongRange): Int {
        var result = 0

        for (i in 0..<stones.lastIndex) for (j in (i + 1)..<stones.size) {
            val s1 = stones[i]
            val s2 = stones[j]

            s1.intersection(s2)
                ?.takeUnless { (x) -> (x.toLong() - s1.pos.x).sign != s1.vel.x.sign }
                ?.takeUnless { (x) -> (x.toLong() - s2.pos.x).sign != s2.vel.x.sign }
                ?.takeUnless { (_, y) -> (y.toLong() - s1.pos.y).sign != s1.vel.y.sign }
                ?.takeUnless { (_, y) -> (y.toLong() - s2.pos.y).sign != s2.vel.y.sign }
                ?.takeIf { it in range }
                ?.let { result++ }
        }

        return result
    }

    fun puzzle2(): Long {
        return 0L
    }

    private operator fun LongRange.contains(point: Pair<Double, Double>) = with(point) {
        first >= start && first <= endInclusive && second >= start && second <= endInclusive
    }

    private data class Stone(val pos: Point3d, val vel: Point3d) {
        private val line = LineCoefficients.of(pos, vel)

        fun intersection(other: Stone) = line.intersection(other.line)
    }

    private data class LineCoefficients(private val a: Long, private val b: Long, private val c: Long) {
        fun intersection(other: LineCoefficients): Pair<Double, Double>? {
            val (a1, b1, c1) = this
            val (a2, b2, c2) = other

            if (a1 * b2 - a2 * b1 == 0L) {
                return null
            }

            val x = (b1.toDouble() * c2 - b2 * c1) / (a1 * b2 - a2 * b1)
            val y = (c1.toDouble() * a2 - c2 * a1) / (a1 * b2 - a2 * b1)

            return x to y
        }

        companion object {
            fun of(pos: Point3d, vel: Point3d): LineCoefficients {
                val (x1, y1) = pos
                val (vx, vy) = vel

                val a = vy
                val b = -vx
                val c = vx * y1 - vy * x1

                return LineCoefficients(a, b, c)
            }
        }
    }

    private data class Point3d(val x: Long, val y: Long, val z: Long) {
        companion object {
            fun of(input: String) =
                input.split(',').map(String::trim).let { (x, y, z) -> Point3d(x.toLong(), y.toLong(), z.toLong()) }
        }
    }
}
