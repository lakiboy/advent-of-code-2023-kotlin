package io.dmitrijs.aoc2023

class Day01(private val input: List<String>) {
    fun puzzle1() = input.sumOf { it.calibrationValue }

    fun puzzle2() = input.sumOf { str ->
        var line = str

        regexHead.find(line)?.let { line = line.replaceDigit(it) }
        regexTail.find(line)?.let { line = line.replaceDigit(it) }

        line.calibrationValue
    }

    private fun String.replaceDigit(match: MatchResult): String {
        val group = match.groups[1]!!

        return replaceRange(group.range, digitsMap.getValue(group.value))
    }

    private val String.calibrationValue get() =
        first { it.isDigit() }.digitToInt() * 10 + last { it.isDigit() }.digitToInt()

    companion object {
        private val digitsAbc = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        private val digitsMap = digitsAbc.mapIndexed { index, word -> word to (index + 1).toString() }.toMap()
        private val digitsBag = digitsAbc.joinToString("|", "(", ")")
        private val regexHead = Regex("^[a-z]*?$digitsBag[a-z0-9]+$")
        private val regexTail = Regex("^[a-z0-9]+$digitsBag[a-z]*?$")
    }
}
