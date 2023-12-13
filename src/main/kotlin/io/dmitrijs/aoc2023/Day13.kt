package io.dmitrijs.aoc2023

class Day13(input: String) {
    private val blocks = input.split("\n\n").map(String::trimEnd)

    fun puzzle1() = blocks.sumOf { getScore(it) }

    fun puzzle2() = blocks.sumOf { oldBlock ->
        val oldScore = getScore(oldBlock)
        oldBlock.withIndex().firstNotNullOf { (index, char) ->
            char.takeIf { it != '\n' }
                ?.let {
                    val newBlock = oldBlock.substring(0, index) + (if (char == '#') '.' else '#') + oldBlock.substring(index + 1)
                    getScore(newBlock, oldScore)
                }?.takeIf { it > 0 }
        }
    }

    private fun getScore(block: String, ignore: Int = -1): Int {
        val horLines = block.lines()
        val verLines = horLines.transpose()

        val maxY = horLines.lastIndex
        val maxX = horLines.first().lastIndex

        return findReflectionAxis(verLines, maxX).firstOrNull { it != ignore }
            ?: findReflectionAxis(horLines, maxY).firstOrNull { (it * 100) != ignore }?.let { it * 100 }
            ?: 0
    }

    private fun findReflectionAxis(matrix: List<String>, maxIndex: Int) =
        matrix.withIndex().zipWithNext().mapNotNull { (a, b) ->
            var (i, line1) = a
            var (j, line2) = b

            val match = i

            while (line1 == line2 && i > 0 && j < maxIndex) {
                line1 = matrix[--i]
                line2 = matrix[++j]
            }

            if (line1 == line2 && (i == 0 || j == maxIndex)) match + 1 else null
        }

    private fun List<String>.transpose() = List(first().length) { x ->
        (0..lastIndex).map { y -> this[y][x] }.joinToString("")
    }
}
