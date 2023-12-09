package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 9")
internal class Day09Test {
    private val exampleInput = resourceAsLines("day09_example")
    private val problemInput = resourceAsLines("day09")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(114, Day09(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(1_581_679_977, Day09(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(2, Day09(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(889, Day09(problemInput).puzzle2())
        }
    }
}
