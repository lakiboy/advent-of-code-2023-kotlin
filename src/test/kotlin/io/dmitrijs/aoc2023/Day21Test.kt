package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 21")
internal class Day21Test {
    @Nested
    inner class Example {
        private val day = Day21(resourceAsLines("day21_example"))

        @Test
        fun puzzle1() {
            assertEquals(16, day.puzzle1(6))
        }

        @Ignore
        @Test
        fun puzzle2() {
            assertEquals(16, day.puzzle2(6))
            assertEquals(50, day.puzzle2(10))
            assertEquals(1_594, day.puzzle2(50))
            assertEquals(6_536, day.puzzle2(100))
            assertEquals(167_004, day.puzzle2(500))
            assertEquals(668_697, day.puzzle2(1_000))
            assertEquals(16_733_044, day.puzzle2(5_000))
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day21(resourceAsLines("day21"))

        @Test
        fun puzzle1() {
            assertEquals(3_773, day.puzzle1(64))
        }

        @Test
        fun puzzle2() {
            assertEquals(625_628_021_226_274, day.puzzle2(26_501_365))
        }
    }
}
