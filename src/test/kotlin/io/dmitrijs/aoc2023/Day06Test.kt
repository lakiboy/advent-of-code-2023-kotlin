package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 6")
internal class Day06Test {
    private val exampleInput = resourceAsLines("day06_example")
    private val problemInput = resourceAsLines("day06")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(288, Day06(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(500_346, Day06(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(71_503, Day06(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(42_515_755, Day06(problemInput).puzzle2())
        }

        @Test
        fun `solves problem with brute force`() {
            assertEquals(42_515_755, Day06(problemInput).puzzle2BruteForce())
        }
    }
}
