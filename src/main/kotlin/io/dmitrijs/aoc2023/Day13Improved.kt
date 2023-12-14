package io.dmitrijs.aoc2023

/**
 * Faster solution with Hamming distance - https://en.wikipedia.org/wiki/Hamming_distance
 */
class Day13Improved(input: String) {
    private val blocks = input.split("\n\n").map(String::trimEnd)

    fun puzzle1() = blocks.sumOf { getScore(it) }

    fun puzzle2() = blocks.sumOf { getScore(it, distance = 1) }

    private fun getScore(block: String, distance: Int = 0): Int {
        val horLines = block.lines()
        val verLines = horLines.transpose()

        val maxY = horLines.lastIndex
        val maxX = horLines.first().lastIndex

        return findReflectionAxis(verLines, maxX, distance)
            ?: findReflectionAxis(horLines, maxY, distance)?.let { it * 100 }
            ?: 0
    }

    private fun findReflectionAxis(matrix: List<String>, maxIndex: Int, distance: Int) =
        matrix.withIndex().zipWithNext().firstNotNullOfOrNull { (a, b) ->
            var (i, line1) = a
            var (j, line2) = b

            val match = i
            var requiredDist = distance - (line1 hammingDistance line2)

            while (requiredDist >= 0 && i > 0 && j < maxIndex) {
                line1 = matrix[--i]
                line2 = matrix[++j]
                requiredDist -= line1 hammingDistance line2
            }

            if (requiredDist == 0 && (i == 0 || j == maxIndex)) match + 1 else null
        }

    private fun List<String>.transpose() = List(first().length) { x ->
        (0..lastIndex).map { y -> this[y][x] }.joinToString("")
    }

    private infix fun String.hammingDistance(other: String) = indices.count { this[it] != other[it] }
}
