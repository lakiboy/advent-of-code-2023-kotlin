package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 3")
internal class Day03Test {
    private val exampleInput = resourceAsLines("day03_example")
    private val problemInput = resourceAsLines("day03")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(4_361, Day03(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(535_351, Day03(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(467_835, Day03(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(87_287_096, Day03(problemInput).puzzle2())
        }
    }
}
