package io.dmitrijs.aoc2023

class Day10(input: List<String>, guess: Char) {
    private lateinit var start: Point
    private val loop: HashSet<Point>
    private val loopSpaces = hashSetOf<Point>()
    private var longestDistance: Int = 0

    private val maxX = input.first().lastIndex
    private val maxY = input.lastIndex
    private val matrix = Array(input.size) { y ->
        CharArray(input[y].length) { x ->
            if (input[y][x] == 'S') guess.also { start = Point(x, y) } else input[y][x]
        }
    }

    init {
        getMainLoop().let { (mainLoop, distance) ->
            loop = mainLoop
            longestDistance = distance
        }

        // Remove rubbish + collect spaces
        matrix.indices.onEach { y ->
            matrix[y].indices.onEach { x ->
                val p = Point(x, y)
                if (p !in loop) matrix[y][x] = '.'
                if (p.space) loopSpaces.add(p)
            }
        }

        // Debug
        println("=".repeat(maxX + 1))
        println(matrix.joinToString("\n") { it.joinToString("") })
    }

    fun puzzle1() = longestDistance

    fun puzzle2(): Int {
        return 0
    }

    private val Point.value get() = matrix[y][x]
    private val Point.space get() = value == '.'
    private val Point.valid get() = x in 0..maxX && y in 0..maxY
    private val Point.asPipe get() = Pipe(value)

    private fun getMainLoop(): Pair<HashSet<Point>, Int> {
        val queue = ArrayDeque(listOf(start to 0))
        val visited = hashSetOf(start)
        var longestDistance = 0

        while (queue.isNotEmpty()) {
            val (node, distance) = queue.removeFirst()
            val pipe = node.asPipe

            if (distance > longestDistance) longestDistance = distance

            Direction.entries
                .map { direction -> direction to (node + direction) }
                .filter { (direction, neighbour) ->
                    neighbour.valid && !neighbour.space && neighbour !in visited && pipe.connectsTo(direction, neighbour.asPipe)
                }
                .onEach { (_, neighbour) ->
                    queue.add(neighbour to (distance + 1))
                    visited.add(neighbour)
                }
        }

        return visited to longestDistance
    }

    private data class Pipe(private val char: Char) {
        fun connectsTo(direction: Direction, other: Pipe) = Pair(char, other.char).let { compare ->
            when (direction) {
                Direction.UP -> compare in setOf(
                    '|' to '|', '|' to 'F', '|' to '7',
                    'L' to '|', 'L' to 'F', 'L' to '7',
                    'J' to '|', 'J' to 'F', 'J' to '7',
                )
                Direction.DOWN -> compare in setOf(
                    '|' to '|', '|' to 'L', '|' to 'J',
                    '7' to '|', '7' to 'L', '7' to 'J',
                    'F' to '|', 'F' to 'L', 'F' to 'J',
                )
                Direction.RIGHT -> compare in setOf(
                    '-' to '-', '-' to '7', '-' to 'J',
                    'L' to '-', 'L' to '7', 'L' to 'J',
                    'F' to '-', 'F' to '7', 'F' to 'J',
                )
                Direction.LEFT -> compare in setOf(
                    '-' to '-', '-' to 'L', '-' to 'F',
                    'J' to '-', 'J' to 'L', 'J' to 'F',
                    '7' to '-', '7' to 'L', '7' to 'F',
                )
            }
        }
    }
}
