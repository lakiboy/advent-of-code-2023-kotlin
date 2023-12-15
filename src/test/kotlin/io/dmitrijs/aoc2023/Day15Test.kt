package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 15")
internal class Day15Test {
    private val exampleInput = resourceAsText("day15_example")
    private val problemInput = resourceAsText("day15")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(1_320, Day15(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(497_373, Day15(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(145, Day15(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(259_356, Day15(problemInput).puzzle2())
        }
    }
}
