package io.dmitrijs.aoc2023

import com.github.shiguruikai.combinatoricskt.permutationsWithRepetition

class Day12(private val input: List<String>) {
    fun puzzle1() = input.sumOf { line: String ->
        val (pattern, numbers) = line.split(' ')
        calculateRecursive(pattern, numbers.split(',').map(String::toInt))
    }

    fun puzzle2() = input.sumOf { line: String ->
        val (p, n) = line.split(' ')
        val pattern = (0..3).fold(p) { acc, _ -> "$acc?$p" }
        val numbers = (0..3).fold(n) { acc, _ -> "$acc,$n" }
        calculateRecursive(pattern, numbers.split(',').map(String::toInt))
    }

    private fun calculateRecursive(pattern: String, numbers: List<Int>): Long {
        if ('?' in pattern) {
            val l = pattern.replaceFirst('?', '#')
            val r = pattern.replaceFirst('?', '.')

            return calculateRecursive(l, numbers) + calculateRecursive(r, numbers)
        }

        val matches = pattern.split('.').filter(String::isNotEmpty)

        return when {
            matches.size != numbers.size -> 0
            matches.zip(numbers).all { (match, count) -> match.length == count } -> 1
            else -> 0
        }
    }

    @Suppress("UnusedPrivateMember")
    private fun calculateWithPermutations(pattern: String, numbers: List<Int>): Long {
        val positions = pattern.mapIndexedNotNull { index, char ->
            index.takeIf { char == '?' }
        }

        return listOf('#', '.').permutationsWithRepetition(positions.size).sumOf { replacement ->
            val matches = pattern
                .toCharArray()
                .apply { replacement.forEachIndexed { index, char -> this[positions[index]] = char } }
                .joinToString("")
                .split('.')
                .filter(String::isNotEmpty)

            when {
                matches.size != numbers.size -> 0L
                matches.zip(numbers).all { (match, count) -> match.length == count } -> 1L
                else -> 0L
            }
        }
    }
}
