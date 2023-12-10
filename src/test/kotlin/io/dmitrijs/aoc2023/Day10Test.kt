package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 10")
internal class Day10Test {
    private val problemInput = resourceAsLines("day10")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            val exampleInput = """
                ..F7.
                .FJ|.
                SJ.L7
                |F--J
                LJ...
            """.trimIndent().lines()
            assertEquals(8, Day10(exampleInput, Direction.RIGHT).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(7_086, Day10(problemInput, Direction.DOWN).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            val exampleInput1 = """
                ...........
                .S-------7.
                .|F-----7|.
                .||.....||.
                .||.....||.
                .|L-7.F-J|.
                .|..|.|..|.
                .L--J.L--J.
                ...........
            """.trimIndent().lines()

            val exampleInput2 = """
                ..........
                .S------7.
                .|F----7|.
                .||....||.
                .||....||.
                .|L-7F-J|.
                .|..||..|.
                .L--JL--J.
                ..........
            """.trimIndent().lines()

            val exampleInput3 = """
                .F----7F7F7F7F-7....
                .|F--7||||||||FJ....
                .||.FJ||||||||L7....
                FJL7L7LJLJ||LJ.L-7..
                L--J.L7...LJS7F-7L7.
                ....F-J..F7FJ|L7L7L7
                ....L7.F7||L7|.L7L7|
                .....|FJLJ|FJ|F7|.LJ
                ....FJL-7.||.||||...
                ....L---J.LJ.LJLJ...
            """.trimIndent().lines()

            val exampleInput4 = """
                FF7FSF7F7F7F7F7F---7
                L|LJ||||||||||||F--J
                FL-7LJLJ||||||LJL-77
                F--JF--7||LJLJ7F7FJ-
                L---JF-JLJ.||-FJLJJ7
                |F|F-JF---7F7-L7L|7|
                |FFJF7L7F-JF7|JL---7
                7-L-JL7||F7|L7F-7F7|
                L.L7LFJ|||||FJL7||LJ
                L7JLJL-JLJLJL--JLJ.L
            """.trimIndent().lines()

            assertEquals(4, Day10(exampleInput1, Direction.RIGHT).puzzle2())
            assertEquals(4, Day10(exampleInput2, Direction.RIGHT).puzzle2())
            assertEquals(8, Day10(exampleInput3, Direction.RIGHT).puzzle2())
            assertEquals(10, Day10(exampleInput4, Direction.DOWN).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(317, Day10(problemInput, Direction.DOWN).puzzle2())
            assertEquals(317, Day10(problemInput, Direction.DOWN).puzzle2InputHack())
        }
    }
}
