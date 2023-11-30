package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsListOfInt
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 0")
internal class Day00Test {
    private val exampleInput = resourceAsListOfInt("day00_example")
    private val problemInput = resourceAsListOfInt("day00")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(6, Day00(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(15, Day00(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(3, Day00(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(5, Day00(problemInput).puzzle2())
        }
    }
}
