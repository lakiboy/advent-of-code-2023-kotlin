package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 20")
internal class Day20Test {
    @Nested
    inner class Example {
        @Test
        fun puzzle1() {
            val exampleInput1 = """
                broadcaster -> a, b, c
                %a -> b
                %b -> c
                %c -> inv
                &inv -> a
            """.trimIndent().lines()

            val exampleInput2 = """
                broadcaster -> a
                %a -> inv, con
                &inv -> b
                %b -> con
                &con -> output
            """.trimIndent().lines()

            assertEquals(32_000_000, Day20(exampleInput1).puzzle1())
            assertEquals(11_687_500, Day20(exampleInput2).puzzle1())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day20(resourceAsLines("day20"))

        @Test
        fun puzzle1() {
            assertEquals(711_650_489, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(0, day.puzzle2())
        }
    }
}
