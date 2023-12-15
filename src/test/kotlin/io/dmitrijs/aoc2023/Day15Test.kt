package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 15")
internal class Day15Test {
    @Nested
    inner class Example {
        private val day = Day15(resourceAsText("day15_example"))

        @Test
        fun puzzle1() {
            assertEquals(1_320, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(145, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day15(resourceAsText("day15"))

        @Test
        fun puzzle1() {
            assertEquals(497_373, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(259_356, day.puzzle2())
        }
    }
}
