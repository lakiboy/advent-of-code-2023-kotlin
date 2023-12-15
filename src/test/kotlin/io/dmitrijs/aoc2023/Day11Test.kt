package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 11")
internal class Day11Test {
    @Nested
    inner class Example {
        private val day = Day11(resourceAsLines("day11_example"))

        @Test
        fun puzzle1() {
            assertEquals(374, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(1_030, day.puzzle2(10))
            assertEquals(8_410, day.puzzle2(100))
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day11(resourceAsLines("day11"))

        @Test
        fun puzzle1() {
            assertEquals(9_418_609, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(593_821_230_983, day.puzzle2(1_000_000))
        }
    }
}
