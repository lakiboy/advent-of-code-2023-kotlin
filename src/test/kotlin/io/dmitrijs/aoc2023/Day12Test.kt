package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 12")
internal class Day12Test {
    private val exampleInput = resourceAsLines("day12_example")
    private val problemInput = resourceAsLines("day12")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(21, Day12(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(7_716, Day12(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    @Ignore
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(525_152, Day12(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(0, Day12(problemInput).puzzle2())
        }
    }
}
