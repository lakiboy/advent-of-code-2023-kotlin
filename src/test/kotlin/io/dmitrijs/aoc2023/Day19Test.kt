package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 19")
internal class Day19Test {
    @Nested
    inner class Example {
        private val day = Day19(resourceAsText("day19_example"))

        @Test
        fun puzzle1() {
            assertEquals(19_114, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(167_409_079_868_000, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day19(resourceAsText("day19"))

        @Test
        fun puzzle1() {
            assertEquals(319_062, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(118_638_369_682_135, day.puzzle2())
        }
    }
}
