package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 7")
internal class Day07Test {
    @Nested
    inner class Example {
        private val day = Day07(resourceAsLines("day07_example"))

        @Test
        fun puzzle1() {
            assertEquals(6_440, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(5_905, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day07(resourceAsLines("day07"))

        @Test
        fun puzzle1() {
            assertEquals(246_409_899, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(244_848_487, day.puzzle2())
        }
    }
}
