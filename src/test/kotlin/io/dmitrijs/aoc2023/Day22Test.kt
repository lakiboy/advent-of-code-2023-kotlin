package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 22")
internal class Day22Test {
    @Nested
    inner class Example {
        private val day = Day22(resourceAsLines("day22_example"))

        @Test
        fun puzzle1() {
            assertEquals(5, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(7, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day22(resourceAsLines("day22"))

        @Test
        fun puzzle1() {
            assertEquals(497, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(67_468, day.puzzle2())
        }
    }
}
