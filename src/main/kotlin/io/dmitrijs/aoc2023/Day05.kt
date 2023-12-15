package io.dmitrijs.aoc2023

import kotlin.math.max
import kotlin.math.min

class Day05(input: String) {
    private val seeds = input
        .substringBefore("\n\n")
        .substringAfter(": ")
        .split(" ")
        .map(String::toLong)

    private val rangesMap = hashMapOf<String, Pair<String, List<MappingRange>>>().apply {
        input.split("\n\n").drop(1).forEach { block ->
            val lines = block.trimEnd().split("\n")
            val (source, _, target) = lines.first().substringBefore(" ").split("-")
            val ranges = lines.drop(1).map { MappingRange.of(it) }.sortedBy { it.first }
            this[source] = target to ranges
        }
    }

    fun puzzle1() = seeds.minOf { n ->
        mapRange(n..n).minOf { it.first }
    }

    fun puzzle2() = seeds.chunked(2).minOf { (n, l) ->
        mapRange(n..<n + l).minOf { it.first }
    }

    private fun mapRange(input: LongRange, phase: String = "seed"): List<LongRange> {
        if (phase == "location") return listOf(input)

        val (target, mappings) = rangesMap.getValue(phase)
        val overallMin = mappings.first().first
        val overallMax = mappings.last().last

        return mappings
            .mapNotNull { mappingRange -> mappingRange.remap(input) }
            .toMutableList()
            .apply {
                if (input.first < overallMin) add(input.first..min(overallMin - 1, input.last))
                if (input.last > overallMax) add(max(overallMax + 1, input.first)..input.last)
                if (isEmpty()) add(input) // Do I have broken input?
            }
            .flatMap { range -> mapRange(range, target) }
    }

    private data class MappingRange(private val range: LongRange, private val delta: Long) {
        val first get() = range.first
        val last get() = range.last

        fun remap(other: LongRange): LongRange? {
            val min = max(first, other.first) + delta
            val max = min(last, other.last) + delta

            return if (min <= max) min..max else null
        }

        companion object {
            fun of(line: String) = line
                .split(" ")
                .map(String::toLong)
                .let { (to, from, len) -> MappingRange(from..<from + len, to - from) }
        }
    }
}
