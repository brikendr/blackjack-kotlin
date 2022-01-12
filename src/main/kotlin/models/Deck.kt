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
        cards.add(Card(score = 2, suit = "C", label = "2"))
        cards.add(Card(score = 3, suit = "C", label = "3"))
        cards.add(Card(score = 4, suit = "C", label = "4"))
        cards.add(Card(score = 5, suit = "C", label = "5"))
        cards.add(Card(score = 6, suit = "C", label = "6"))
        cards.add(Card(score = 7, suit = "C", label = "7"))
        cards.add(Card(score = 8, suit = "C", label = "8"))
        cards.add(Card(score = 9, suit = "C", label = "9"))
        cards.add(Card(score = 10, suit = "C", label = "10"))
        cards.add(Card(score = 10, suit = "C", label = "J"))
        cards.add(Card(score = 10, suit = "C", label = "Q"))
        cards.add(Card(score = 10, suit = "C", label = "K"))
        cards.add(Card(score = 11, suit = "C", label = "A"))

        cards.add(Card(score = 2, suit = "D", label = "2"))
        cards.add(Card(score = 3, suit = "D", label = "3"))
        cards.add(Card(score = 4, suit = "D", label = "4"))
        cards.add(Card(score = 5, suit = "D", label = "5"))
        cards.add(Card(score = 6, suit = "D", label = "6"))
        cards.add(Card(score = 7, suit = "D", label = "7"))
        cards.add(Card(score = 8, suit = "D", label = "8"))
        cards.add(Card(score = 9, suit = "D", label = "9"))
        cards.add(Card(score = 10, suit = "D", label = "10"))
        cards.add(Card(score = 10, suit = "D", label = "J"))
        cards.add(Card(score = 10, suit = "D", label = "Q"))
        cards.add(Card(score = 10, suit = "D", label = "K"))
        cards.add(Card(score = 11, suit = "D", label = "A"))

        cards.add(Card(score = 2, suit = "H", label = "2"))
        cards.add(Card(score = 3, suit = "H", label = "3"))
        cards.add(Card(score = 4, suit = "H", label = "4"))
        cards.add(Card(score = 5, suit = "H", label = "5"))
        cards.add(Card(score = 6, suit = "H", label = "6"))
        cards.add(Card(score = 7, suit = "H", label = "7"))
        cards.add(Card(score = 8, suit = "H", label = "8"))
        cards.add(Card(score = 9, suit = "H", label = "9"))
        cards.add(Card(score = 10, suit = "H", label = "10"))
        cards.add(Card(score = 10, suit = "H", label = "J"))
        cards.add(Card(score = 10, suit = "H", label = "Q"))
        cards.add(Card(score = 10, suit = "H", label = "K"))
        cards.add(Card(score = 11, suit = "H", label = "A"))

        cards.add(Card(score = 2, suit = "S", label = "2"))
        cards.add(Card(score = 3, suit = "S", label = "3"))
        cards.add(Card(score = 4, suit = "S", label = "4"))
        cards.add(Card(score = 5, suit = "S", label = "5"))
        cards.add(Card(score = 6, suit = "S", label = "6"))
        cards.add(Card(score = 7, suit = "S", label = "7"))
        cards.add(Card(score = 8, suit = "S", label = "8"))
        cards.add(Card(score = 9, suit = "S", label = "9"))
        cards.add(Card(score = 10, suit = "S", label = "10"))
        cards.add(Card(score = 10, suit = "S", label = "J"))
        cards.add(Card(score = 10, suit = "S", label = "Q"))
        cards.add(Card(score = 10, suit = "S", label = "K"))
        cards.add(Card(score = 11, suit = "S", label = "A"))
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
            val cardList: List<String> = it.split(",")
            for(card in cardList) {
                val suit: Char = card.trim()[0]
                val scoreStr: String = card.trim().substring(1)
                val score: Int = Card.parseScore(scoreStr)
                cards.add(Card(score = score, suit = suit.toString(), label = scoreStr))
            }
        }
    }

    fun printDeck() {
        println(cards)
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
