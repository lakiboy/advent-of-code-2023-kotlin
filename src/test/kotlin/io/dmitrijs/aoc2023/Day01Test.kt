package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 1")
internal class Day01Test {
    private val problemInput = resourceAsLines("day01")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(142, Day01(resourceAsLines("day01_example_a")).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(54_632, Day01(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(281, Day01(resourceAsLines("day01_example_b")).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(54_019, Day01(problemInput).puzzle2())
        }
    }
}
