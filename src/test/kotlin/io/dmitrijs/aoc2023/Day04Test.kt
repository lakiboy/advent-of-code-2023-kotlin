package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 4")
internal class Day04Test {
    private val exampleInput = resourceAsLines("day04_example")
    private val problemInput = resourceAsLines("day04")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(13, Day04(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(26_443, Day04(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(30, Day04(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(6_284_877, Day04(problemInput).puzzle2())
        }
    }
}
