package io.dmitrijs.aoc2023

class Day05(input: String) {
    private val hashingMap = hashMapOf<String, Pair<String, Set<HashingRange>>>()
    private val seeds: List<Long>

    init {
        val blocks = input.split("\n\n")
        seeds = blocks.first().split(": ")[1].split(" ").map(String::toLong)

        blocks.drop(1).forEach { block ->
            val lines = block.trimEnd().split("\n")
            val (source, _, target) = lines.first().split(" ").first().split("-")
            val hashing = lines
                .drop(1)
                .map { line ->
                    line.split(" ")
                        .map(String::toLong)
                        .let { (target, source, range) -> HashingRange(source, target, range) }
                }.toSet()

            hashingMap[source] = target to hashing
        }
    }

    fun puzzle1() = seeds.minOf { it.hash() }

    fun puzzle2() = seeds.chunked(2).minOf { (source, range) ->
        var result = Long.MAX_VALUE
        var number = source
        val target = number + range

        do {
            val value = number.hash()
            if (result > value) result = value
        } while (++number < target)

        result
    }

    private fun Long.hash(): Long {
        var phase = "seed"
        var value = this

        do {
            val (target, hashing) = hashingMap.getValue(phase)
            hashing.firstOrNull { value in it }?.let { value = it.hash(value) }
            phase = target
        } while (phase != "location")

        return value
    }

    private data class HashingRange(val source: Long, val target: Long, val range: Long) {
        operator fun contains(num: Long) = num in source until (source + range)

        fun hash(num: Long) = num + (target - source)
    }
}
