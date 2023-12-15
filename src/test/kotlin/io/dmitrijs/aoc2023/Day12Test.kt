package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 12")
internal class Day12Test {
    @Nested
    inner class Example {
        private val day = Day12(resourceAsLines("day12_example"))

        @Test
        fun puzzle1() {
            assertEquals(21, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(525_152, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day12(resourceAsLines("day12"))

        @Test
        fun puzzle1() {
            assertEquals(7_716, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(18_716_325_559_999, day.puzzle2())
        }
    }
}
