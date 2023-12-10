package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 5")
internal class Day05Test {
    private val exampleInput = resourceAsText("day05_example")
    private val problemInput = resourceAsText("day05")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(35, Day05(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(600_279_879, Day05(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(46, Day05(exampleInput).puzzle2BruteForce())
        }

//        @Test
//        fun `solves problem with brute force`() {
//            assertEquals(20_191_102, Day05(problemInput).puzzle2())
//        }
    }
}
