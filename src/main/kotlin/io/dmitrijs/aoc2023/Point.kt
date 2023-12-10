package io.dmitrijs.aoc2023

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = copy(x = x + other.x, y = y + other.y)

    operator fun plus(other: Direction) = plus(other.move)

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

enum class Direction(val move: Point) {
    UP(move = Point(0, -1)),
    DOWN(move = Point(0, 1)),
    LEFT(move = Point(-1, 0)),
    RIGHT(move = Point(1, 0));

    companion object
}
