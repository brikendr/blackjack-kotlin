package models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.expect

import com.github.stefanbirkner.systemlambda.SystemLambda.*

internal class DeckTest {

    @Test
    fun testInit() {
        val deck = Deck()
        assertEquals(52, deck.cardsInDeck())
    }

    @Test
    fun clear() {
        val deck = Deck()
        deck.clear()
        assertEquals(0, deck.cardsInDeck())
    }

    @Test
    fun testReadFromFile() {
        val deck = Deck()
        deck.readFromFile("src/test/kotlin/TestDeckInput.txt")
        assertEquals(10, deck.cardsInDeck())

        var expectedDeck = arrayListOf<Card>(
            Card(10, "C", "C10"),
            Card(6, "S", "S6"),
            Card(10, "S", "SQ"),
            Card(10, "D", "D10"),
            Card(10, "D", "DK"),
            Card(11, "H", "HA"),
            Card(10, "D", "DJ"),
            Card(5, "C", "C5"),
            Card(6, "D", "D6"),
            Card(3, "S", "S3")
        )
        var index = 0
        val deckCards = deck.getDeckOfCards()
        for (card in expectedDeck) {
            assertEquals(card, deckCards[index])
            index += 1
        }
    }

    @Test
    fun testReadFromFileNonExistent() {
        val deck = Deck()
        deck.clear()

        val stdOut = tapSystemOut {
            deck.readFromFile("a/b/c.txt")
        }
        assertEquals("File a/b/c.txt does not exist!\n",stdOut)
        assertEquals(0, deck.cardsInDeck())
    }

    @Test
    fun testReadFromStr() {
        var deckSequence: String = "CA, D5, S8, DK"
        val deck = Deck()
        deck.readFromStr(deckSequence)

        var expectedDeck = arrayListOf<Card>(
            Card(11, "C", "CA"),
            Card(5, "D", "D5"),
            Card(8, "S", "S8"),
            Card(10, "D", "DK")
        )
        var index = 0
        val deckCards = deck.getDeckOfCards()
        for (card in expectedDeck) {
            assertEquals(card, deckCards[index])
            index += 1
        }
    }

    @Test
    fun testPrintDeck() {
        val deck = Deck()
        deck.readFromFile("src/test/kotlin/TestDeckInput.txt")
        val stdOut = tapSystemOut {
            deck.printDeck()
        }

        assertEquals("[C10, S6, SQ, D10, DK, HA, DJ, C5, D6, S3]\n", stdOut)
    }

    @Test
    fun testDrawCard() {
        val deck = Deck()
        deck.readFromFile("src/test/kotlin/TestDeckInput.txt")
        var card = deck.drawCard()!!
        assertEquals("C10", card.label)
        assertEquals(10, card.score)
        assertEquals("C", card.suit)

        card = deck.drawCard()!!
        assertEquals("S6", card.label)
        assertEquals(6, card.score)
        assertEquals("S", card.suit)
    }

    @Test
    fun testDrawOnEmptyDeck() {
        val deck = Deck()
        deck.clear()
        var card: Card? = null
        val stdOut = tapSystemOut {
            card = deck.drawCard()
        }
        assertEquals("No more elements to draw from deck!\n", stdOut)
        assertEquals(null, card)
    }

    @Test
    fun cardsInDeck() {
        val deck = Deck()
        assertEquals(52, deck.cardsInDeck())
        deck.readFromFile("src/test/kotlin/TestDeckInput.txt")
        assertEquals(10, deck.cardsInDeck())
    }
}