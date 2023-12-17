package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 17")
internal class Day17Test {
    @Nested
    inner class Example {
        private val day = Day17(resourceAsLines("day17_example"))

        @Test
        fun puzzle1() {
            assertEquals(102, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(94, day.puzzle2())

            // Additional example.
            val input = """
                111111111111
                999999999991
                999999999991
                999999999991
                999999999991
            """.trimIndent().lines()
            assertEquals(71, Day17(input).puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day17(resourceAsLines("day17"))

        @Test
        fun puzzle1() {
            assertEquals(698, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(825, day.puzzle2())
        }
    }
}
