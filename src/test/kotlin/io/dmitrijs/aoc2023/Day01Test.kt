package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 1")
internal class Day01Test {
    @Nested
    inner class Example {
        @Test
        fun puzzle1() {
            assertEquals(142, Day01(resourceAsLines("day01_example_a")).puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(281, Day01(resourceAsLines("day01_example_b")).puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day01(resourceAsLines("day01"))

        @Test
        fun puzzle1() {
            assertEquals(54_632, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(54_019, day.puzzle2())
        }
    }
}
