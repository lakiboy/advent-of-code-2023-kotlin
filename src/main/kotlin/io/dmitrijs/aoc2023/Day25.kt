package io.dmitrijs.aoc2023

private typealias ComponentGraph = MutableMap<String, MutableSet<String>>

class Day25(private val input: List<String>) {
    fun puzzle1(): Int {
        val edges = input.flatMap { line ->
            val v = line.substringBefore(": ")
            line.substringAfter(": ").split(" ").map { u -> Edge(v, u) }
        }
        val nodes = (edges.map { it.v } + edges.map { it.u })
            .toSet()
            .withIndex()
            .associate { (node, index) -> index to node }

        do {
            val (cutSize, dus) = kargerMinCut(nodes, edges)
            if (cutSize == 3) {
                val sets = mutableMapOf<Int, Int>()
                nodes.forEach { (_, index) ->
                    val set = find(index, dus)
                    sets[set] = sets.getOrPut(set) { 0 }.inc()
                }
                require(sets.size == 2)

                return sets.values.reduce { a, b -> a * b }
            }
        } while (true)
    }

    private fun kargerMinCut(nodes: Map<String, Int>, edges: List<Edge>): Pair<Int, IntArray> {
        val parent = IntArray(nodes.size) { it }
        val rank = IntArray(nodes.size)

        fun findRoot(node: String) = find(nodes.getValue(node), parent)

        val edgesLeft = edges.toMutableList()
        var vertices = nodes.size

        // Union-Find
        while (vertices > 2) {
            val edge = edgesLeft.random()
            val setV = findRoot(edge.v)
            val setU = findRoot(edge.u)

            if (setU != setV) {
                vertices--
                union(setV, setU, parent, rank)
            }

            edgesLeft.remove(edge)
        }

        val cutSize = edgesLeft.count { findRoot(it.v) != findRoot(it.u) }

        return cutSize to parent
    }

    private fun find(node: Int, parent: IntArray): Int {
        if (node == parent[node]) return node

        // With path compression
        return find(parent[node], parent).also { parent[node] = it }
    }

    private fun union(vRoot: Int, uRoot: Int, parent: IntArray, rank: IntArray) = when {
        rank[vRoot] < rank[uRoot] -> parent[vRoot] = uRoot
        rank[vRoot] > rank[uRoot] -> parent[uRoot] = vRoot
        else -> {
            parent[vRoot] = uRoot
            rank[uRoot] += 1
        }
    }

    private data class Edge(val v: String, val u: String)

    private fun dot(): Int {
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
