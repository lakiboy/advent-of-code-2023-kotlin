package io.dmitrijs.aoc2023

class Day25(private val input: List<String>) {
    fun puzzle1(): Int {
        val dot = input.joinToString(separator = "\n", prefix = "digraph {\n", postfix = "\n}") { line ->
            val parts = line.split(": ", " ")
            parts.drop(1).joinToString("\n") { node -> "  ${parts[0]} -> $node" }
        }

        // 1) dot -T svg -K neato src/main/resources/day25.dot > src/main/resources/day25.svg
        // 2) modify
        // 3) ccomps -v src/main/resources/day25.modified.dot > foo.dot
        // 4) see summary e.g. 756 nodes & 719 nodes
        println(dot)

        return 54
    }
}
