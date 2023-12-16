package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 16")
internal class Day16Test {
    @Nested
    inner class Example {
        private val day = Day16(resourceAsLines("day16_example"))

        @Test
        fun puzzle1() {
            assertEquals(46, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(51, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day16(resourceAsLines("day16"))

        @Test
        fun puzzle1() {
            assertEquals(7_788, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(7_987, day.puzzle2())
        }
    }
}
