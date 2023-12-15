package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 8")
internal class Day08Test {
    @Nested
    inner class Example {
        @Test
        fun puzzle1() {
            val exampleInput = """
                RL

                AAA = (BBB, CCC)
                BBB = (DDD, EEE)
                CCC = (ZZZ, GGG)
                DDD = (DDD, DDD)
                EEE = (EEE, EEE)
                GGG = (GGG, GGG)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent().lines()
            assertEquals(2, Day08(exampleInput).puzzle1())
        }

        @Test
        fun puzzle2() {
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
            """.trimIndent().lines()
            assertEquals(6, Day08(exampleInput).puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day08(resourceAsLines("day08"))

        @Test
        fun puzzle1() {
            assertEquals(19_099, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(17_099_847_107_071, day.puzzle2())
        }
    }
}
