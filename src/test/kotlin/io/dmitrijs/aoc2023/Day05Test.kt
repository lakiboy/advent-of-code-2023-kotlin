package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 5")
internal class Day05Test {
    @Nested
    inner class Example {
        private val day = Day05(resourceAsText("day05_example"))

        @Test
        fun puzzle1() {
            assertEquals(35, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(46, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day05(resourceAsText("day05"))

        @Test
        fun puzzle1() {
            assertEquals(600_279_879, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(20_191_102, day.puzzle2())
        }
    }
}
