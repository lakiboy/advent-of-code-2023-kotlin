package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 2")
internal class Day02Test {
    private val exampleInput = resourceAsLines("day02_example")
    private val problemInput = resourceAsLines("day02")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(8, Day02(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(2_101, Day02(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(2_286, Day02(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(58_269, Day02(problemInput).puzzle2())
        }
    }
}
