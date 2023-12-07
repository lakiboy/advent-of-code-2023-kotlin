package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 7")
internal class Day07Test {
    private val exampleInput = resourceAsLines("day07_example")
    private val problemInput = resourceAsLines("day07")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(6_440, Day07(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(246_409_899, Day07(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(5_905, Day07(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(244_848_487, Day07(problemInput).puzzle2())
        }
    }
}
