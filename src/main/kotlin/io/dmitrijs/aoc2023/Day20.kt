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
                val (switch, signal) = queue.removeFirst()

                if (signal) high++ else low++

                if (switch in conjs) {
                    val s = !conjs.getValue(switch).all { state.getValue(it) }
                    state[switch] = s
                    board.getValue(switch).forEach { queue.add(it to s) }
                } else if (!signal && switch in state) {
                    val s = !state.getValue(switch)
                    state[switch] = s
                    board.getValue(switch).forEach { queue.add(it to s) }
                }
            }
        }

        return low * high
    }

    @Suppress("NestedBlockDepth")
    fun puzzle2(): Long {
        val state = board.keys.associateWith { false }.toMutableMap()
        val queue = ArrayDeque<Pair<String, Boolean>>()
        var press = 0

        // [ks, pm, dl, vk] -> dt -> rx
        val highSignalPressMap = hashMapOf("ks" to 0, "pm" to 0, "dl" to 0, "vk" to 0)

        do {
            press++

            queue.apply {
                board.getValue("broadcaster").forEach { add(it to false) }
            }

            while (queue.isNotEmpty()) {
                val (switch, signal) = queue.removeFirst()
                if (switch in conjs) {
                    val s = !conjs.getValue(switch).all { state.getValue(it) }
                    state[switch] = s
                    board.getValue(switch).forEach { queue.add(it to s) }

                    // Record high signal for switches monitored.
                    if (s && switch in highSignalPressMap) highSignalPressMap[switch] = press
                } else if (!signal) {
                    val s = !state.getValue(switch)
                    state[switch] = s
                    board.getValue(switch).forEach { queue.add(it to s) }
                }
            }
        } while (highSignalPressMap.values.any { it == 0 })

        // LCM has no effect on my input.
        return lcm(highSignalPressMap.values.map(Int::toLong))
    }
}
