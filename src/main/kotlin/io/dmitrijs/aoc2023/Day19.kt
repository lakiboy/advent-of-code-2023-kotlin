package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Day19.Expr.Accept
import io.dmitrijs.aoc2023.Day19.Expr.Compare
import io.dmitrijs.aoc2023.Day19.Expr.Jump
import io.dmitrijs.aoc2023.Day19.Expr.Reject

class Day19(input: String) {
    private val environments = input.substringAfter("\n\n").trimEnd().lines().map { line ->
        buildMap {
            line.trim('{', '}').split(',').forEach { expr ->
                put(expr.substringBefore('='), expr.substringAfter('=').toLong())
            }
        }
    }

    private val workflowMap = input.substringBefore("\n\n").lines().associate { line ->
        line.substringBefore('{') to line.substringAfter('{').trimEnd('}').split(',').map { it.toExpr() }
    }

    fun puzzle1() = environments.sumOf { env ->
        fun Compare.predicate() = when (op) {
            '>' -> env.getValue(name) > value
            '<' -> env.getValue(name) < value
            else -> error("Invalid operation $op supplied.")
        }

        var accept = false
        val expressions = workflowMap.getValue("in").toMutableList()

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
                    expressions.addAll(workflowMap.getValue(expr.next))
                }
            }
        } while (!accept)

        if (accept) env.values.sum() else 0L
    }

    @Suppress("FunctionOnlyReturningConstant")
    fun puzzle2(): Long {
        return 0L
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
