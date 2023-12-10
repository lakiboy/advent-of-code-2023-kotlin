package io.dmitrijs.aoc2023

// | is a vertical pipe connecting north and south.
// - is a horizontal pipe connecting east and west.
// L is a 90-degree bend connecting north and east.
// J is a 90-degree bend connecting north and west.
// 7 is a 90-degree bend connecting south and west.
// F is a 90-degree bend connecting south and east.
// . is ground; there is no pipe in this tile.
// S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
class Day10(private val input: List<String>) {
    private val maxX = input.first().length
    private val maxY = input.size
    private lateinit var start: Point
    private var spaces = mutableSetOf<Point>()

    init {
        input.indices.forEach { y ->
            input[y].indices.forEach { x ->
                val p = Point(x, y)
                when (p.value) {
                    'S' -> start = Point(x, y)
                    '.' -> spaces.add(p)
                }
            }
        }
    }

    fun puzzle1(guess: Char): Int {
        fun Point.toPipe() = if (this == start) Pipe(guess) else Pipe(value)

        val queue = ArrayDeque<Pair<Point, Int>>()
        val visited = hashSetOf<Point>()
        var longestDistance = 0

        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (node, distance) = queue.removeFirst()
            val currentPipe = node.toPipe()

            if (distance > longestDistance) {
                longestDistance = distance
            }

            Direction.entries
                .map { direction -> direction to (node + direction) }
                .filter { (direction, neighbour) -> neighbour.valid && neighbour !in visited && currentPipe.connectsTo(direction, neighbour.toPipe()) }
                .onEach { (_, neighbour) ->
                    queue.add(neighbour to (distance + 1))
                    visited.add(neighbour)
                }
        }

        return longestDistance
    }

    fun puzzle2(guess: Char): Int {
        fun Point.toPipe() = if (this == start) Pipe(guess) else Pipe(value)

        var leakCount = 0
        val newSpaces = spaces.toHashSet()

        val leak = ArrayDeque<Triple<Point, Direction, Boolean>>()
        val totalSpaces = newSpaces.size

        val visited = hashSetOf<Point>()

        println("====")
        println("Total spaces: $totalSpaces")

        while (newSpaces.isNotEmpty()) {
            val space = newSpaces.first()
            var isLeak = false

            var junkTiles = 0
            var extraLeaks = 0
            val newVisited = hashSetOf<Point>()
            newVisited.add(space)

            leak.clear()
            leak.add(Triple(space, Direction.DOWN, false))
            visited.add(space)

            while (leak.isNotEmpty()) {
                val (node, currentDirection, currentLeak) = leak.removeFirst()
                val currentNodeIsSpace = node.value == '.'

                if (currentLeak && node.value == '.') extraLeaks++

                if (!isLeak && node.border && currentNodeIsSpace) {
                    isLeak = true
                }

                val allNeighbours = node.neighbours()

                Direction.entries.map { direction -> direction to node + direction }.filter { (_, neighbour) -> neighbour.valid2 && neighbour !in visited }.forEach { (direction, neighbour) ->
                    if (currentNodeIsSpace && neighbour.value == '.') {
                        // if (currentLeak) extraLeaks++

                        leak.add(Triple(neighbour, direction, false))
                        visited.add(neighbour)
                        newVisited.add(neighbour)
                    } else if (currentNodeIsSpace) {
                        val asPipe = neighbour.toPipe()
                        val hasLeaks = Direction.entries.map { neighbourDirection ->
                            neighbourDirection to neighbour + neighbourDirection
                        }.filter { (_, connection) ->
                            connection.valid && connection !in visited && connection in allNeighbours
                        }.count { (neighbourDirection, connection) ->
                            !asPipe.connectsTo(neighbourDirection, connection.toPipe())
                        }

                        if (hasLeaks > 0) {
                            leak.add(Triple(neighbour, direction, currentLeak))
                            visited.add(neighbour)
                            newVisited.add(neighbour)
                        }
                    } else {
                        if (direction == currentDirection) {
                            if (neighbour.value == '.' || node.toPipe().connectsTo(direction, neighbour.toPipe())) {
                                // if (neighbour.value == '.') extraLeaks++

                                leak.add(Triple(neighbour, direction, true))
                                visited.add(neighbour)
                                newVisited.add(neighbour)
                            }
                        }
                    }
                }
            }

            leakCount += if (isLeak) newVisited.filter { it.value == '.' }.onEach { println(it) }.size else extraLeaks

            newSpaces.removeAll(newVisited)
        }

        println(visited.size)

        return totalSpaces - leakCount
    }

    private val Point.border get() = x == 0 || y == 0 || x == maxX - 1 || y == maxY - 1
    private val Point.valid get() = x in 0 until maxX && y in 0 until maxY && value != '.'
    private val Point.valid2 get() = x in 0 until maxX && y in 0 until maxY
    private val Point.value get() = input[y][x]

    private fun Point.spaceNeighbours() = orthogonalNeighbours().filter { it.valid2 && it.value == '.' }

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

        companion object {
            val pipes = setOf('|', '-', 'F', '7', 'J', 'L')
        }
    }
}
