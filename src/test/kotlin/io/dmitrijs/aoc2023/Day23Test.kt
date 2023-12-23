package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 23")
internal class Day23Test {
    @Nested
    inner class Example {
        private val day = Day23(resourceAsLines("day23_example"))

        @Test
        fun puzzle1() {
            assertEquals(94, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(154, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day23(resourceAsLines("day23"))

        @Test
        fun puzzle1() {
            assertEquals(2_130, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(5018, day.puzzle2()) // 5018
        }
    }
}
