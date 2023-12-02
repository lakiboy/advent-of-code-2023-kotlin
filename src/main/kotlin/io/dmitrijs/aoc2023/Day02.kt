package io.dmitrijs.aoc2023

// Single expression solutions for the sake of fun.
class Day02(private val input: List<String>) {
    fun puzzle1() = input.sumOf { line ->
        line.split(": ").let { (start, games) ->
            games.split("; ").any { game ->
                game.split(", ").any { cubes ->
                    cubes.examine().let { (color, count) ->
                        count > totalCubes.getValue(color)
                    }
                }
            }.let { failed ->
                if (failed) 0 else start.substringAfter(" ").toInt()
            }
        }
    }

    fun puzzle2() = input.sumOf { line ->
        line.split(": ").let { (_, games) ->
            mutableMapOf("red" to 0, "green" to 0, "blue" to 0).apply {
                games.split("; ").forEach { game ->
                    game.split(", ").forEach { cubes ->
                        cubes.examine().let { (color, count) ->
                            if (count > getValue(color)) put(color, count)
                        }
                    }
                }
            }.values.product()
        }
    }

    private fun String.examine() = substringAfter(" ") to substringBefore(" ").toInt()

    private fun Collection<Int>.product() = fold(1) { acc, i -> acc * i }

    companion object {
        private val totalCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
    }
}
