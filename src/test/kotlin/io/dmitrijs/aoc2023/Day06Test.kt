package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 6")
internal class Day06Test {
    @Nested
    inner class Example {
        private val day = Day06(resourceAsLines("day06_example"))

        @Test
        fun puzzle1() {
            assertEquals(288, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(71_503, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day06(resourceAsLines("day06"))

        @Test
        fun puzzle1() {
            assertEquals(500_346, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(42_515_755, day.puzzle2())
            assertEquals(42_515_755, day.puzzle2BruteForce())
        }
    }
}
