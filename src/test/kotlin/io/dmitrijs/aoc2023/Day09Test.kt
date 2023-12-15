package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 9")
internal class Day09Test {
    @Nested
    inner class Example {
        private val day = Day09(resourceAsLines("day09_example"))

        @Test
        fun puzzle1() {
            assertEquals(114, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(2, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day09(resourceAsLines("day09"))

        @Test
        fun puzzle1() {
            assertEquals(1_581_679_977, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(889, day.puzzle2())
        }
    }
}
