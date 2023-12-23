# Advent of Code 2023 in Kotlin

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/lakiboy/advent-of-code-2023-kotlin.svg?style=svg&circle-token=6d4e4a5bdf4f0e2902c4d54750ff1d7df65cda5c)](https://dl.circleci.com/status-badge/redirect/gh/lakiboy/advent-of-code-2023-kotlin/tree/main)

My solutions (WIP) for the [Advent of Code 2023](https://adventofcode.com/2023) puzzles in [Kotlin](https://kotlinlang.org).

## Puzzles

| Day | Title                           | Task                                         | Test input                                                                                          | Solution                                                                                                                      | Test                                                     |
|-----|---------------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------|
| 1   | Trebuchet?!                     | [Docs](https://adventofcode.com/2023/day/1)  | [Text 1](src/test/resources/day01_example_a.txt) / [Text 2](src/test/resources/day01_example_b.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day01.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day01Test.kt) |
| 2   | Cube Conundrum                  | [Docs](https://adventofcode.com/2023/day/2)  | [Text](src/test/resources/day02_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day02.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day02Test.kt) |
| 3   | Gear Ratios                     | [Docs](https://adventofcode.com/2023/day/3)  | [Text](src/test/resources/day03_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day03.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day03Test.kt) |
| 4   | Scratchcards                    | [Docs](https://adventofcode.com/2023/day/4)  | [Text](src/test/resources/day04_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day04.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day04Test.kt) |
| 5   | If You Give A Seed A Fertilizer | [Docs](https://adventofcode.com/2023/day/5)  | [Text](src/test/resources/day05_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day05.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day05Test.kt) |
| 6   | Wait For It                     | [Docs](https://adventofcode.com/2023/day/6)  | [Text](src/test/resources/day06_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day06.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day06Test.kt) |
| 7   | Camel Cards                     | [Docs](https://adventofcode.com/2023/day/7)  | [Text](src/test/resources/day07_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day07.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day07Test.kt) |
| 8   | Haunted Wasteland               | [Docs](https://adventofcode.com/2023/day/8)  | -                                                                                                   | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day08.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day08Test.kt) |
| 9   | Mirage Maintenance              | [Docs](https://adventofcode.com/2023/day/9)  | [Text](src/test/resources/day09_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day09.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day09Test.kt) |
| 10  | Pipe Maze                       | [Docs](https://adventofcode.com/2023/day/10) | -                                                                                                   | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day10.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day10Test.kt) |
| 11  | Cosmic Expansion                | [Docs](https://adventofcode.com/2023/day/11) | [Text](src/test/resources/day11_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day11.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day11Test.kt) |
| 12  | Hot Springs                     | [Docs](https://adventofcode.com/2023/day/12) | [Text](src/test/resources/day12_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day12.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day12Test.kt) |
| 13  | Point of Incidence              | [Docs](https://adventofcode.com/2023/day/13) | [Text](src/test/resources/day13_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day13.kt) / [Improved](src/main/kotlin/io/dmitrijs/aoc2023/Day13Improved.kt)     | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day13Test.kt) |
| 14  | Parabolic Reflector Dish        | [Docs](https://adventofcode.com/2023/day/14) | [Text](src/test/resources/day14_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day14.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day14Test.kt) |
| 15  | Lens Library                    | [Docs](https://adventofcode.com/2023/day/15) | [Text](src/test/resources/day15_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day15.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day15Test.kt) |
| 16  | The Floor Will Be Lava          | [Docs](https://adventofcode.com/2023/day/16) | [Text](src/test/resources/day16_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day16.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day16Test.kt) |
| 17  | Clumsy Crucible                 | [Docs](https://adventofcode.com/2023/day/17) | [Text](src/test/resources/day17_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day17.kt) / [Simplified](src/main/kotlin/io/dmitrijs/aoc2023/Day17Simplified.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day17Test.kt) |
| 18  | Lavaduct Lagoon                 | [Docs](https://adventofcode.com/2023/day/18) | [Text](src/test/resources/day18_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day18.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day18Test.kt) |
| 19  | Aplenty                         | [Docs](https://adventofcode.com/2023/day/19) | [Text](src/test/resources/day19_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day19.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day19Test.kt) |
| 20  | Pulse Propagation               | [Docs](https://adventofcode.com/2023/day/20) | -                                                                                                   | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day20.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day20Test.kt) |
| 21  | Step Counter                    | [Docs](https://adventofcode.com/2023/day/21) | [Text](src/test/resources/day21_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day21.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day21Test.kt) |
| 22  | Sand Slabs                      | [Docs](https://adventofcode.com/2023/day/22) | [Text](src/test/resources/day22_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day22.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day22Test.kt) |
| 23  | A Long Walk                     | [Docs](https://adventofcode.com/2023/day/23) | [Text](src/test/resources/day23_example.txt)                                                        | [Source](src/main/kotlin/io/dmitrijs/aoc2023/Day23.kt)                                                                        | [Test](src/test/kotlin/io/dmitrijs/aoc2023/Day23Test.kt) |
