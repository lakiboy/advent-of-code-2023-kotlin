package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 8")
internal class Day08Test {
    private val problemInput = resourceAsLines("day08")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            val exampleInput = """
                RL

                AAA = (BBB, CCC)
                BBB = (DDD, EEE)
                CCC = (ZZZ, GGG)
                DDD = (DDD, DDD)
                EEE = (EEE, EEE)
                GGG = (GGG, GGG)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent()
            assertEquals(2, Day08(exampleInput.lines()).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(19_099, Day08(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            val exampleInput = """
                LR

                11A = (11B, XXX)
                11B = (XXX, 11Z)
                11Z = (11B, XXX)
                22A = (22B, XXX)
                22B = (22C, 22C)
                22C = (22Z, 22Z)
                22Z = (22B, 22B)
                XXX = (XXX, XXX)
            """.trimIndent()
            assertEquals(6, Day08(exampleInput.lines()).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(17_099_847_107_071, Day08(problemInput).puzzle2())
        }
    }
}
