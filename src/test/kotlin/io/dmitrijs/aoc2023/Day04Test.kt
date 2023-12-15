package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 4")
internal class Day04Test {
    @Nested
    inner class Example {
        private val day = Day04(resourceAsLines("day04_example"))

        @Test
        fun puzzle1() {
            assertEquals(13, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(30, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day04(resourceAsLines("day04"))

        @Test
        fun puzzle1() {
            assertEquals(26_443, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(6_284_877, day.puzzle2())
        }
    }
}
