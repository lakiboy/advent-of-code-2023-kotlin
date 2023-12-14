package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 13")
internal class Day13Test {
    private val exampleInput = resourceAsText("day13_example")
    private val problemInput = resourceAsText("day13")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(405, Day13(exampleInput).puzzle1())
            assertEquals(405, Day13Improved(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(29_130, Day13(problemInput).puzzle1())
            assertEquals(29_130, Day13Improved(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(400, Day13(exampleInput).puzzle2())
            assertEquals(400, Day13Improved(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(33_438, Day13(problemInput).puzzle2())
            assertEquals(33_438, Day13Improved(problemInput).puzzle2())
        }
    }
}
