package io.dmitrijs.aoc2023

class Day08(input: List<String>) {
    private val moves = input.first()
    private val jumps = buildMap {
        input.drop(2).onEach { line ->
            line.trimEnd(')')
                .split(" = (", ", ")
                .let { (k, l, r) -> put(k, l to r) }
        }
    }

    fun puzzle1(): Int {
        var steps = 0
        var phase = "AAA"

        do {
            phase = phase.jump(moves[steps++ % moves.length])
        } while (phase != "ZZZ")

        return steps
    }

    fun puzzle2(): Long {
        var steps = 0
        val phase = jumps.keys.filter { it.endsWith('A') }.toMutableList()
        val stops = IntArray(phase.size)

        do {
            val dir = moves[steps++ % moves.length]
            phase.indices.forEach { i ->
                phase[i] = phase[i].jump(dir)
                if (stops[i] == 0 && phase[i].endsWith("Z")) stops[i] = steps
            }
        } while (stops.any { it == 0 })

        return lcm(stops.map { it.toLong() })
    }

    private fun String.jump(direction: Char) = if (direction == 'L') {
        jumps.getValue(this).first
    } else {
        jumps.getValue(this).second
    }

    private fun lcm(items: List<Long>) = items.reduce { lcm, num -> lcm(lcm, num) }
    private fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)

    private fun gcd(a: Long, b: Long): Long {
        var (num1, num2) = a to b

        while (num2 != 0L)
            num2 = (num1 % num2).also { num1 = num2 }

        return num1
    }
}
