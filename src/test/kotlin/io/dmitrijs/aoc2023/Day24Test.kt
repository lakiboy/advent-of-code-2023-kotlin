package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 24")
internal class Day24Test {
    @Nested
    inner class Example {
        private val day = Day24(resourceAsLines("day24_example"))

        @Test
        fun puzzle1() {
            assertEquals(2, day.puzzle1(7L..27L, 1_000))
        }

        @Test
        fun puzzle2() {
            assertEquals(0, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day24(resourceAsLines("day24"))

        @Test
        fun puzzle1() {
            assertEquals(0, day.puzzle1(200_000_000_000_000L..400_000_000_000_000L, Int.MAX_VALUE))
        }

        @Test
        fun puzzle2() {
            assertEquals(0, day.puzzle2())
        }
    }
}
