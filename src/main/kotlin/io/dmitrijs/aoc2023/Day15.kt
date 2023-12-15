package io.dmitrijs.aoc2023

class Day15(input: String) {
    private val items = input.trimEnd().split(',')

    fun puzzle1() = items.sumOf { it.hash() }

    fun puzzle2(): Int {
        val boxes = Array(256) { mutableListOf<Pair<String, String>>() }

        items.forEach { op ->
            val (lense, value) = op.split('-', '=')
            val slots = boxes[lense.hash()]
            val index = slots.indexOfFirst { (label, _) -> label == lense }

            when {
                '-' in op && index >= 0 -> slots.removeAt(index)
                '=' in op && index >= 0 -> slots[index] = lense to value
                '=' in op -> slots.add(lense to value)
            }
        }

        return boxes.withIndex().sumOf { (num, slots) ->
            slots.withIndex().sumOf { (pos, slot) -> (num + 1) * (pos + 1) * slot.second.toInt() }
        }
    }

    private fun String.hash() = fold(0) { acc, c -> (acc + c.code) * 17 % 256 }
}
