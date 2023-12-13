package io.dmitrijs.aoc2023

import com.github.shiguruikai.combinatoricskt.permutationsWithRepetition

class Day12(private val input: List<String>) {
    fun puzzle1() = input.sumOf { line: String ->
        val (pattern, pockets) = line.split(' ')
        calculateByGroups(pattern, pockets.split(',').map(String::toInt))
    }

    fun puzzle2() = input.sumOf { line: String ->
        val (p, n) = line.split(' ')
        val pattern = (0..3).fold(p) { acc, _ -> "$acc?$p" }
        val pockets = (0..3).fold(n) { acc, _ -> "$acc,$n" }
        calculateByGroups(pattern, pockets.split(',').map(String::toInt))
    }

    @Suppress("CyclomaticComplexMethod")
    private fun calculateByGroups(pattern: String, pockets: List<Int>, cache: HashMap<String, Long> = hashMapOf()): Long {
        val cacheKey = pattern + pockets.toString()
        if (cacheKey in cache) return cache.getValue(cacheKey)

        return when {
            pockets.isEmpty() -> if (pattern.any { it == '#' }) 0 else 1
            pattern.isEmpty() -> if (pockets.isEmpty()) 1 else 0
            pattern.startsWith('.') -> calculateByGroups(pattern.trimStart('.'), pockets, cache)
            pattern.startsWith('?') -> {
                val l = pattern.replaceFirst('?', '#')
                val r = pattern.replaceFirst('?', '.')
                calculateByGroups(l, pockets, cache) + calculateByGroups(r, pockets, cache)
            }
            else -> {
                val len = pockets.first()
                when {
                    pattern.length < len -> 0
                    pattern.length > len && pattern[len] == '#' -> 0
                    pattern.substring(0, len).any { it == '.' } -> 0
                    pattern.length == len -> calculateByGroups("", pockets.drop(1), cache)
                    else -> calculateByGroups(pattern.substring(len + 1), pockets.drop(1), cache)
                }
            }
        }.also { cache[cacheKey] = it }
    }

    /* Recursive slow version */
    @Suppress("UnusedPrivateMember")
    private fun calculateRecursive(pattern: String, pockets: List<Int>): Long {
        if ('?' in pattern) {
            val l = pattern.replaceFirst('?', '#')
            val r = pattern.replaceFirst('?', '.')

            return calculateRecursive(l, pockets) + calculateRecursive(r, pockets)
        }

        val matches = pattern.split('.').filter(String::isNotEmpty)

        return when {
            matches.size != pockets.size -> 0
            matches.zip(pockets).all { (match, count) -> match.length == count } -> 1
            else -> 0
        }
    }

    /** Non-recursive slow version */
    @Suppress("UnusedPrivateMember")
    private fun calculateWithPermutations(pattern: String, pockets: List<Int>): Long {
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
                matches.size != pockets.size -> 0L
                matches.zip(pockets).all { (match, count) -> match.length == count } -> 1L
                else -> 0L
            }
        }
    }
}
