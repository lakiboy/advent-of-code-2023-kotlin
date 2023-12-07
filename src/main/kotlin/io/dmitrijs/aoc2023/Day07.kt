package io.dmitrijs.aoc2023

import io.dmitrijs.aoc2023.Day07.Type.FIVE_OF_A_KIND
import io.dmitrijs.aoc2023.Day07.Type.FOUR_OF_A_KIND
import io.dmitrijs.aoc2023.Day07.Type.FULL_HOUSE
import io.dmitrijs.aoc2023.Day07.Type.HIGH_CARD
import io.dmitrijs.aoc2023.Day07.Type.ONE_PAIR
import io.dmitrijs.aoc2023.Day07.Type.THREE_OF_A_KIND
import io.dmitrijs.aoc2023.Day07.Type.TWO_PAIR

class Day07(private val input: List<String>) {
    fun puzzle1() = solve("AKQJT98765432")

    fun puzzle2() = solve("AKQT98765432J")

    private fun solve(deck: String): Long {
        val hands = input
            .map { line ->
                val cards = line.substringBefore(" ")
                val score = line.substringAfter(" ").toLong()
                recognize(cards, deck) to score
            }.sortedBy { it.first }

        // Smallest first
        return hands.withIndex().sumOf { (index, hand) -> (index + 1) * hand.second }
    }

    @Suppress("CyclomaticComplexMethod")
    private fun recognize(cards: String, deck: String): Hand {
        val joker = deck.last() == JOKER
        val count = deck.map { card -> cards.count { (!joker && card == it) || (joker && card == it && card != JOKER) } }
            .sortedDescending()
            .toMutableList()

        // Jokers go to the strongest position.
        if (joker) count[0] += cards.count { it == JOKER }

        val one = count[0]
        val two = count[1]
        val type = when {
            one == 5 -> FIVE_OF_A_KIND
            one == 4 -> FOUR_OF_A_KIND
            one == 3 && two == 2 -> FULL_HOUSE
            one == 3 -> THREE_OF_A_KIND
            one == 2 && two == 2 -> TWO_PAIR
            one == 2 -> ONE_PAIR
            else -> HIGH_CARD
        }

        return Hand(type, deck, cards)
    }

    private enum class Type { FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD }

    private data class Hand(private val type: Type, private val deck: String, private val cards: String) : Comparable<Hand> {
        private val rank get() = Type.entries.indexOf(type)

        override fun compareTo(other: Hand) =
            if (rank - other.rank != 0) {
                other.rank - rank
            } else {
                (0..4).firstNotNullOfOrNull { index ->
                    val a = deck.indexOf(cards[index])
                    val b = deck.indexOf(other.cards[index])
                    if (a != b) b - a else null
                } ?: error("Invalid card comparison.")
            }
    }

    companion object {
        private const val JOKER = 'J'
    }
}
