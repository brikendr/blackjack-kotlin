package models

import java.io.File

class Deck {
    private val cards: ArrayList<Card> = ArrayList()

    init {
        populateDeck()
    }

    private fun shuffle() {
        cards.shuffle()
    }

    fun populateDeck() {
        cards.add(Card(score = 2, suit = "C", label = "C2"))
        cards.add(Card(score = 3, suit = "C", label = "C3"))
        cards.add(Card(score = 4, suit = "C", label = "C4"))
        cards.add(Card(score = 5, suit = "C", label = "C5"))
        cards.add(Card(score = 6, suit = "C", label = "C6"))
        cards.add(Card(score = 7, suit = "C", label = "C7"))
        cards.add(Card(score = 8, suit = "C", label = "C8"))
        cards.add(Card(score = 9, suit = "C", label = "C9"))
        cards.add(Card(score = 10, suit = "C", label = "C10"))
        cards.add(Card(score = 10, suit = "C", label = "CJ"))
        cards.add(Card(score = 10, suit = "C", label = "CQ"))
        cards.add(Card(score = 10, suit = "C", label = "CK"))
        cards.add(Card(score = 11, suit = "C", label = "CA"))

        cards.add(Card(score = 2, suit = "D", label = "D2"))
        cards.add(Card(score = 3, suit = "D", label = "D3"))
        cards.add(Card(score = 4, suit = "D", label = "D4"))
        cards.add(Card(score = 5, suit = "D", label = "D5"))
        cards.add(Card(score = 6, suit = "D", label = "D6"))
        cards.add(Card(score = 7, suit = "D", label = "D7"))
        cards.add(Card(score = 8, suit = "D", label = "D8"))
        cards.add(Card(score = 9, suit = "D", label = "D9"))
        cards.add(Card(score = 10, suit = "D", label = "D10"))
        cards.add(Card(score = 10, suit = "D", label = "DJ"))
        cards.add(Card(score = 10, suit = "D", label = "DQ"))
        cards.add(Card(score = 10, suit = "D", label = "DK"))
        cards.add(Card(score = 11, suit = "D", label = "DA"))

        cards.add(Card(score = 2, suit = "H", label = "H2"))
        cards.add(Card(score = 3, suit = "H", label = "H3"))
        cards.add(Card(score = 4, suit = "H", label = "H4"))
        cards.add(Card(score = 5, suit = "H", label = "H5"))
        cards.add(Card(score = 6, suit = "H", label = "H6"))
        cards.add(Card(score = 7, suit = "H", label = "H7"))
        cards.add(Card(score = 8, suit = "H", label = "H8"))
        cards.add(Card(score = 9, suit = "H", label = "H9"))
        cards.add(Card(score = 10, suit = "H", label = "H10"))
        cards.add(Card(score = 10, suit = "H", label = "HJ"))
        cards.add(Card(score = 10, suit = "H", label = "HQ"))
        cards.add(Card(score = 10, suit = "H", label = "HK"))
        cards.add(Card(score = 11, suit = "H", label = "HA"))

        cards.add(Card(score = 2, suit = "S", label = "S2"))
        cards.add(Card(score = 3, suit = "S", label = "S3"))
        cards.add(Card(score = 4, suit = "S", label = "S4"))
        cards.add(Card(score = 5, suit = "S", label = "S5"))
        cards.add(Card(score = 6, suit = "S", label = "S6"))
        cards.add(Card(score = 7, suit = "S", label = "S7"))
        cards.add(Card(score = 8, suit = "S", label = "S8"))
        cards.add(Card(score = 9, suit = "S", label = "S9"))
        cards.add(Card(score = 10, suit = "S", label = "S10"))
        cards.add(Card(score = 10, suit = "S", label = "SJ"))
        cards.add(Card(score = 10, suit = "S", label = "SQ"))
        cards.add(Card(score = 10, suit = "S", label = "SK"))
        cards.add(Card(score = 11, suit = "S", label = "SA"))
        shuffle() // Give it a shuffle after initialization
    }

    fun clear() {
        cards.clear()
    }

    fun readFromFile(filePath: String) {
        if(!File(filePath).isFile) {
            println("File $filePath does not exist!")
            return
        }

        // Clear deck
        clear()

        // Initialize new deck from input file
        File(filePath).forEachLine {
            readFromStr(it)
        }
    }

    fun readFromStr(deckSequence: String) {
        if (deckSequence.trim() == "") {
            println("Deck sequence must not be empty!")
            return
        }

        // Clear existing deck
        clear()

        // Initialize deck from input string
        val cardList: List<String> = deckSequence.split(",")
        for(card in cardList) {
            val suit: Char = card.trim()[0]
            val scoreStr: String = card.trim().substring(1)
            val score: Int = Card.parseScore(scoreStr)
            cards.add(Card(score = score, suit = suit.toString(), label = card.trim()))
        }
    }

    fun printDeck() {
        println(cards)
    }

    fun getDeckOfCards(): ArrayList<Card> {
        return cards
    }

    fun drawCard(): Card? {
        if(cards.isNotEmpty()) {
            return cards.removeAt(0)
        }
        println("No more elements to draw from deck!")
        return null
    }

    fun cardsInDeck(): Int {
        return cards.size
    }
}
