package io.dmitrijs.aoc2023

import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = copy(x = x + other.x, y = y + other.y)

    operator fun plus(other: Direction) = plus(other.move)

    fun distanceTo(other: Point) = (x - other.x).absoluteValue + (y - other.y).absoluteValue

    fun neighbours() = setOf(
        copy(y = y - 1),
        copy(x = x + 1, y = y - 1),
        copy(x = x + 1),
        copy(x = x + 1, y = y + 1),
        copy(y = y + 1),
        copy(x = x - 1, y = y + 1),
        copy(x = x - 1),
        copy(x = x - 1, y = y - 1),
    )

    fun orthogonalNeighbours() = setOf(
        copy(x = x - 1),
        copy(x = x + 1),
        copy(y = y - 1),
        copy(y = y + 1),
    )
}

data class Point3d(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Point3d) = copy(x = x + other.x, y = y + other.y, z = z + other.z)

    companion object {
        fun of(str: String) = str.split(",").map(String::toInt).let { (x, y, z) -> Point3d(x, y, z) }
    }
}

enum class Direction(val move: Point) {
    UP(move = Point(0, -1)),
    DOWN(move = Point(0, 1)),
    LEFT(move = Point(-1, 0)),
    RIGHT(move = Point(1, 0));

    companion object
}
