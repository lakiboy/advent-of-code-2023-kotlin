package io.dmitrijs.aoc2023

class Day06(input: List<String>) {
    private val time: List<String> = input[0].toNumbers()
    private val dist: List<String> = input[1].toNumbers()

    fun puzzle1() = time
        .indices
        .map { countWins(time[it].toLong(), dist[it].toLong()) }
        .fold(1L) { acc, i -> acc * i }

    fun puzzle2() = countWins(
        time.joinToString("").toLong(),
        dist.joinToString("").toLong(),
    )

    private fun countWins(time: Long, dist: Long) = (0..time).count { speed -> (time - speed) * speed > dist }

    private fun String.toNumbers() = Regex("\\d+").findAll(this).map(MatchResult::value).toList()
}
