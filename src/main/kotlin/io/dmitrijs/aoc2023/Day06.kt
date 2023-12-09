package io.dmitrijs.aoc2023

import kotlin.math.floor
import kotlin.math.sqrt

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

    fun puzzle2BruteForce() = countWinsBruteForce(
        time.joinToString("").toLong(),
        dist.joinToString("").toLong(),
    )

    /**
     * Quadratic equation.
     *
     * speed * (time - speed) > dist
     * -speed^2 + speed * time - dist > 0
     * a = -1
     * b = time
     * c = -dist
     * d = b^2 - 4ac
     * d = time^2 - (4 * -1 * -dist)
     * d = time^2 - (4 * dist)
     */
    private fun countWins(time: Long, dist: Long): Long {
        val d = time * time - 4 * dist
        val s = sqrt(d.toDouble())
        val x1 = (time + s) / 2
        val x2 = (time - s) / 2

        return if (floor(x1) == x1) x1.toLong() - x2.toLong() - 1 else x1.toLong() - x2.toLong()
    }

    private fun countWinsBruteForce(time: Long, dist: Long) = (0..time).count { speed -> (time - speed) * speed > dist }

    private fun String.toNumbers() = Regex("\\d+").findAll(this).map(MatchResult::value).toList()
}
