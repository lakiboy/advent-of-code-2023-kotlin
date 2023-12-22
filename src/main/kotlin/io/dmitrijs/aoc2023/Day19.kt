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
        println(workflowMap)

        // digraph {
        //    a -> b[label="0.2",weight="0.2"];
        //    a -> c[label="0.4",weight="0.4"];
        //    c -> b[label="0.6",weight="0.6"];
        //    c -> e[label="0.6",weight="0.6"];
        //    e -> e[label="0.1",weight="0.1"];
        //    e -> b[label="0.7",weight="0.7"];
        // }

        // px{a<2006:qkq,m>2090:A,rfg}
        // pv{a>1716:R,A}
        // lnx{m>1548:A,A}
        // rfg{s<537:gd,x>2440:R,A}
        // qs{s>3448:A,lnx}
        // qkq{x<1416:A,crn}
        // crn{x>2662:A,R}
        // in{s<1351:px,qqz}
        // qqz{s>2770:qs,m<1801:hdj,R}
        // gd{a>3333:R,R}
        // hdj{m>838:A,pv}

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
