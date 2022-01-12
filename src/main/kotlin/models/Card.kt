package models

import java.lang.NumberFormatException

data class Card(val score: Int, val suit: String, val label: String) {
    override fun toString(): String {
        return "${label}"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Card) return score == other?.score && suit == other?.suit
        return false
    }

    companion object {
        fun parseScore(score: String): Int {
            return when(score) {
                "A" -> 11
                "K" -> 10
                "Q" -> 10
                "J" -> 10
                else -> {
                    try {
                        score.toInt()
                    } catch (e: NumberFormatException) {
                        0
                    }
                }
            }
        }
    }
}
