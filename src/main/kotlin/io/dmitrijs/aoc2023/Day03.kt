package io.dmitrijs.aoc2023

@Suppress("NestedBlockDepth")
class Day03(private val input: List<String>) {
    private val maxX = input.first.lastIndex
    private val maxY = input.lastIndex

    fun puzzle1(): Long {
        val result = mutableListOf<Long>()
        var number = ""
        var append = false

        input.indices.forEach { y ->
            input[y].indices.forEach { x ->
                val p = Point(x, y)
                if (p.isDigit) {
                    number += p.value
                    if (!append && p.validNeighbours().any { it.isChar }) append = true
                }
                if ((!p.isDigit || p.x == maxX) && number.isNotEmpty()) {
                    if (append) result.add(number.toLong())
                    number = ""
                    append = false
                }
            }
        }

        return result.sum()
    }

    fun puzzle2(): Long {
        val result = hashMapOf<Point, MutableList<Long>>()
        var number = ""
        var append = false

        val currentGears = hashSetOf<Point>()

        input.indices.forEach { y ->
            input[y].indices.forEach { x ->
                val p = Point(x, y)
                if (p.isDigit) {
                    number += p.value
                    p.validNeighbours().filter { it.isChar }.forEach {
                        append = true
                        if (it.isGear) currentGears.add(it)
                    }
                }
                if ((!p.isDigit || p.x == maxX) && number.isNotEmpty()) {
                    if (append && currentGears.isNotEmpty()) {
                        currentGears.forEach { gear ->
                            if (gear in result) {
                                result.getValue(gear).add(number.toLong())
                            } else {
                                result[gear] = mutableListOf(number.toLong())
                            }
                        }
                    }
                    number = ""
                    append = false
                    currentGears.clear()
                }
            }
        }

        return result.filterValues { it.size == 2 }.values.sumOf { it[0] * it[1] }
    }

    private val Point.value get() = input[y][x]
    private val Point.isChar get() = !isSpace && !isDigit
    private val Point.isGear get() = value == '*'
    private val Point.isSpace get() = value == '.'
    private val Point.isDigit get() = value.isDigit()
    private fun Point.validNeighbours() = neighbours().filter { it.x in 0..maxX && it.y in 0..maxY }
}
