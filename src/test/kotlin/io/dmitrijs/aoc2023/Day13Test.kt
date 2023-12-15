package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 13")
internal class Day13Test {
    @Nested
    inner class Example {
        private val day = Day13(resourceAsText("day13_example"))
        private val dayImproved = Day13Improved(resourceAsText("day13_example"))

        @Test
        fun puzzle1() {
            assertEquals(405, day.puzzle1())
            assertEquals(405, dayImproved.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(400, day.puzzle2())
            assertEquals(400, dayImproved.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day13(resourceAsText("day13"))
        private val dayImproved = Day13Improved(resourceAsText("day13"))

        @Test
        fun puzzle1() {
            assertEquals(29_130, day.puzzle1())
            assertEquals(29_130, dayImproved.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(33_438, day.puzzle2())
            assertEquals(33_438, dayImproved.puzzle2())
        }
    }
}
