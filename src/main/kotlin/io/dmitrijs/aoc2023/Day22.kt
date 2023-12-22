package io.dmitrijs.aoc2023

import kotlin.math.max
import kotlin.math.min

class Day22(input: List<String>) {
    private val bricks = input.map { line ->
        Brick(
            a = Point3d.of(line.substringBefore('~')),
            b = Point3d.of(line.substringAfter('~')),
        )
    }.sortedBy { it.minZ }.toMutableList()

    init {
        bricks.indices.forEach { i ->
            while (bricks[i].canFall()) {
                bricks[i] = bricks[i].fall()
            }
        }
    }

    fun puzzle1() = bricks.safeToRemove().size

    // Slowish
    fun puzzle2() = (bricks - bricks.safeToRemove().toSet()).sumOf { base ->
        towerCount(setOf(base), bricks, 0)
    }

    private fun List<Brick>.safeToRemove() = filter { brick ->
        bricks.filter { brick.below(it) }.all { above ->
            bricks.any { it != brick && it.surfaceFor(above) }
        }
    }

    private tailrec fun towerCount(base: Set<Brick>, tower: List<Brick>, count: Int): Int {
        val next = base.flatMap { brick ->
            tower.filter { brick.surfaceFor(it) }.filter { above ->
                tower.none { it !in base && it.surfaceFor(above) }
            }
        }.toSet()

        if (next.isEmpty()) return count

        return towerCount(next, tower - next, count + next.size)
    }

    private fun Brick.canFall() = minZ > 1 && bricks.none { it.surfaceFor(this) }

    private data class Brick(private val a: Point3d, private val b: Point3d) {
        val minZ get() = min(a.z, b.z)
        val maxZ get() = max(a.z, b.z)

        fun fall() = copy(a = a.fall(), b = b.fall())

        fun below(other: Brick) = this != other && maxZ == other.minZ - 1

        fun surfaceFor(other: Brick) = below(other) && flatIntersects(other)

        fun flatIntersects(other: Brick) = when {
            (a.x > other.b.x || b.x < other.a.x) -> false
            (a.y > other.b.y || b.y < other.a.y) -> false
            else -> true
        }

        private fun Point3d.fall() = copy(z = z - 1)
    }
}
