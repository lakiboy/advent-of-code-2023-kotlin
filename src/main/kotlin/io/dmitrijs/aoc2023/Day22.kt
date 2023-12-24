package io.dmitrijs.aoc2023

import kotlin.math.max
import kotlin.math.min

class Day22(input: List<String>) {
    private val bricks = input.map { line ->
        Brick(
            a = Point3d.of(line.substringBefore('~')),
            b = Point3d.of(line.substringAfter('~')),
        )
    }.sorted().toMutableList()

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
        bricks.filter { brick.surfaceFor(it) }.all { above ->
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

    private data class Brick(private val a: Point3d, private val b: Point3d) : Comparable<Brick> {
        val minZ get() = min(a.z, b.z)
        val maxZ get() = max(a.z, b.z)

        fun fall() = copy(a = a.fall(), b = b.fall())

        fun surfaceFor(other: Brick) = this != other && maxZ == other.minZ - 1 && flatIntersects(other)

        fun flatIntersects(other: Brick) = when {
            (a.x > other.b.x || b.x < other.a.x) -> false
            (a.y > other.b.y || b.y < other.a.y) -> false
            else -> true
        }

        override fun compareTo(other: Brick) = minZ - other.minZ

        private fun Point3d.fall() = copy(z = z - 1)
    }

    private data class Point3d(val x: Int, val y: Int, val z: Int) {
        operator fun plus(other: Point3d) = copy(x = x + other.x, y = y + other.y, z = z + other.z)

        companion object {
            fun of(str: String) = str.split(",").map(String::toInt).let { (x, y, z) -> Point3d(x, y, z) }
        }
    }
}
