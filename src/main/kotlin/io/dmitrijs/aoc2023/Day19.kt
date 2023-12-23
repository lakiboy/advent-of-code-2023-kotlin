package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Day19.Expr.Accept
import io.dmitrijs.aoc2023.Day19.Expr.Compare
import io.dmitrijs.aoc2023.Day19.Expr.Jump
import io.dmitrijs.aoc2023.Day19.Expr.Reject
import kotlin.math.max
import kotlin.math.min

private typealias VariableRanges = List<Pair<Int, Int>>

class Day19(input: String) {
    private val environments = input.substringAfter("\n\n").trimEnd().lines().map { line ->
        buildMap {
            line.trim('{', '}').split(',').forEach { expr ->
                put(expr.substringBefore('='), expr.substringAfter('=').toLong())
            }
        }
    }

    private val workflowMap = input.substringBefore("\n\n").lines().associate { line ->
        line.substringBefore('{') to line.substringAfter('{').trimEnd('}').split(',')
    }

    fun puzzle1() = environments.sumOf { env ->
        fun Compare.predicate() = when (op) {
            '>' -> env.getValue(name) > value
            '<' -> env.getValue(name) < value
            else -> error("Invalid operation $op supplied.")
        }

        var accept = false
        val expressions = workflowMap
            .getValue("in")
            .map { it.toExpr() }
            .toMutableList()

        do {
            when (val expr = expressions.removeFirst()) {
                is Accept -> accept = true
                is Reject -> break
                is Compare -> if (expr.predicate()) {
                    expressions.clear()
                    expressions.add(expr.expr)
                }
                is Jump -> {
                    expressions.clear()
                    expressions.addAll(workflowMap.getValue(expr.next).map { it.toExpr() })
                }
            }
        } while (!accept)

        if (accept) env.values.sum() else 0L
    }

    fun puzzle2() = traverseRanges("in", List(4) { 1 to 4_000 })

    private fun splitRanges(expr: String, ranges: VariableRanges): Pair<VariableRanges, VariableRanges> {
        val (name, split) = expr
            .substringBefore(':')
            .split('<', '>')
            .let { (n, v) -> n to v.toInt() }
        val index = "xmas".indexOf(name)
        val range = ranges[index]

        return when {
            '>' in expr -> {
                val l = ranges.toMutableList().apply { this[index] = max(range.first, split + 1) to range.second }
                val r = ranges.toMutableList().apply { this[index] = range.first to min(range.second, split) }
                l to r
            }
            '<' in expr -> {
                val l = ranges.toMutableList().apply { this[index] = range.first to min(range.second, split - 1) }
                val r = ranges.toMutableList().apply { this[index] = max(range.first, split) to range.second }
                l to r
            }
            else -> error("Invalid expression '$expr'.")
        }
    }

    private fun traverseRanges(workflow: String, ranges: VariableRanges): Long = when (workflow) {
        "R" -> 0L
        "A" -> ranges.variations()
        else -> {
            var result = 0L
            val expressions = workflowMap.getValue(workflow)
            var currentRanges = ranges

            for (index in 0..<expressions.lastIndex) {
                val (left, right) = splitRanges(expressions[index], currentRanges)
                if (left.variations() > 0) {
                    result += traverseRanges(expressions[index].substringAfter(':'), left)
                }
                currentRanges = right
            }

            result + traverseRanges(expressions.last(), currentRanges)
        }
    }

    private fun VariableRanges.variations() = fold(1L) { acc, range ->
        acc * (if (range.second < range.first) 0 else (range.second - range.first + 1))
    }

    private fun String.toExpr(): Expr = when {
        "A" == this -> Accept
        "R" == this -> Reject
        ">" in this -> split('>', ':').let { (name, value, next) -> Compare(name, '>', value.toLong(), next.toExpr()) }
        "<" in this -> split('<', ':').let { (name, value, next) -> Compare(name, '<', value.toLong(), next.toExpr()) }
        else -> Jump(this)
    }

    private sealed class Expr {
        data object Accept : Expr()
        data object Reject : Expr()
        data class Jump(val next: String) : Expr()
        data class Compare(val name: String, val op: Char, val value: Long, val expr: Expr) : Expr()
    }
}
