package io.dmitrijs.aoc2023

import kotlin.math.max
import kotlin.math.min

class Day24(input: List<String>) {
    private val stones = input.map { line ->
        val (pos, vel) = line.split(" @ ")
        Stone(Point3d.of(pos), Point3d.of(vel))
    }

    fun puzzle1(range: LongRange, iter: Int): Int {
        val segment = Line.of(range)
        val movingStones = stones.toMutableList()
        val intersections = mutableSetOf<Pair<Point3d, Point3d>>()

        repeat(iter) { index ->
            if (index % 100_000 == 0) {
                println("Iter $index; Size: ${intersections.size}")
            }

            for (i in 0..<movingStones.lastIndex) for (j in (i + 1)..<movingStones.size) {
                val s1 = movingStones[i]
                val s2 = movingStones[j]

                s1.intersection(s2)
                    ?.takeIf { it in segment }
                    ?.let { intersections.add(s1.pos to s2.pos) }
            }

            for (i in movingStones.indices) {
                movingStones[i] = movingStones[i].move()
            }
        }

        return intersections.size
    }

    fun puzzle2(): Long {
        return 0L
    }

    private data class Stone(val pos: Point3d, val cur: Point3d, val vel: Point3d) {
        constructor(pos: Point3d, vel: Point3d) : this(pos, pos, vel)

        val line2d get() = Line(
            x1 = min(pos.x, cur.x).toDouble(),
            x2 = max(pos.x, cur.x).toDouble(),
            y1 = min(pos.y, cur.y).toDouble(),
            y2 = max(pos.y, cur.y).toDouble(),
        )

        fun move() = copy(cur = cur + vel)

        fun intersection(other: Stone): Pair<Double, Double>? {
            val (x1, y1) = pos
            val (x2, y2) = cur
            val (x3, y3) = other.pos
            val (x4, y4) = other.cur

            val determinant = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4)

            // Lines are parallel.
            if (determinant == 0L) return null

            val px = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / determinant.toDouble()
            val py = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / determinant.toDouble()
            val p = px to py

            return p.takeIf { it in line2d && it in other.line2d }
        }
    }

    private data class Line(val x1: Double, val x2: Double, val y1: Double, val y2: Double) {
        init {
            require(x1 <= x2)
            require(y1 <= y2)
        }

        operator fun contains(point: Pair<Double, Double>) = with(point) {
            first in x1..x2 && second in y1..y2
        }

        companion object {
            fun of(range: LongRange) = Line(
                x1 = range.first.toDouble(),
                x2 = range.last.toDouble(),
                y1 = range.first.toDouble(),
                y2 = range.last.toDouble(),
            )
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
