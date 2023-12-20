package io.dmitrijs.aoc2023

class Day20(input: List<String>) {
    private val board = mutableMapOf<String, List<String>>()
    private val conjs = mutableMapOf<String, MutableSet<String>>()

    init {
        input.forEach { line ->
            val switch = line.substringBefore(" -> ")
            val source = switch.trimStart('%', '&')

            board[source] = line.substringAfter(" -> ").split(", ")

            if (switch.startsWith('&')) {
                conjs[source] = mutableSetOf()
            }
        }
        board.forEach { (source, targets) ->
            targets.filter { it in conjs }.map { conj -> conjs.getValue(conj).add(source) }
        }
    }

    fun puzzle1(): Long {
        val state = board.keys.associateWith { false }.toMutableMap()
        val queue = ArrayDeque<Pair<String, Boolean>>()

        var low = 0L
        var high = 0L

        repeat(1_000) {
            low++

            queue.apply {
                board.getValue("broadcaster").forEach { add(it to false) }
            }

            while (queue.isNotEmpty()) {
                val (target, signal) = queue.removeFirst()

                if (signal) high++ else low++

                if (target in conjs) {
                    val s = !conjs.getValue(target).all { state.getValue(it) }
                    state[target] = s
                    board.getValue(target).onEach { queue.add(it to s) }
                } else if (!signal && target in state) {
                    val s = !state.getValue(target)
                    state[target] = s
                    board.getValue(target).onEach { queue.add(it to s) }
                }
            }
        }

        return low * high
    }

    @Suppress("FunctionOnlyReturningConstant")
    fun puzzle2(): Long {
        return 0L
    }
}
