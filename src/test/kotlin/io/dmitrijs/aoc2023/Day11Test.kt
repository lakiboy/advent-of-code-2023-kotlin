package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 11")
internal class Day11Test {
    private val exampleInput = resourceAsLines("day11_example")
    private val problemInput = resourceAsLines("day11")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(374, Day11(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(9_418_609, Day11(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(1_030, Day11(exampleInput).puzzle2(10))
            assertEquals(8_410, Day11(exampleInput).puzzle2(100))
        }

        @Test
        fun `solves problem`() {
            assertEquals(593_821_230_983, Day11(problemInput).puzzle2(1_000_000))
        }
    }
}
