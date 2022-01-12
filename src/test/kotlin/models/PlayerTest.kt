package models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import com.github.stefanbirkner.systemlambda.SystemLambda.*

internal class PlayerTest {

    @Test
    fun testHandCard() {
        val card = Card(11, "C", "CA")
        val jimmy = Player("Jimmy", false)
        jimmy.handCard(card)
        assertEquals(1, jimmy.getHand().size)
        assertEquals(card, jimmy.getHand()[0])
    }

    @Test
    fun testCalculateHandScore() {
        val jimmy = Player("Jimmy", false)
        val hand = arrayListOf<Card>(
            Card(11, "C", "CA"),
            Card(10, "C", "CK"),
            Card(10, "C", "CK")
        )
        for (card in hand) {
            jimmy.handCard(card)
        }

        assertEquals(31, jimmy.calculateHandScore())
    }

    @Test
    fun testPlayerReset() {
        val card = Card(11, "C", "CA")
        val jimmy = Player("Jimmy", false)
        jimmy.handCard(card)
        jimmy.reset()
        assertEquals(0, jimmy.calculateHandScore())
        assertEquals(0, jimmy.getHand().size)
    }

    @Test
    fun testHasBlackjack() {
        val goodHand = arrayListOf<Card>(
            Card(11, "C", "CA"),
            Card(10, "C", "CK")
        )
        val jimmy = Player("Jimmy", false)
        for (card in goodHand) jimmy.handCard(card)
        assertEquals(true, jimmy.hasBlackjack())
        val badHand = arrayListOf<Card>(
            Card(5, "C", "C5"),
            Card(2, "D", "D2")
        )
        jimmy.reset()
        for (card in badHand) jimmy.handCard(card)
        assertEquals(false, jimmy.hasBlackjack())
    }

    @Test
    fun testDraw() {
        val deck = Deck();
        //C10, S6, SQ, D10, DK, HA, DJ, C5, D6, S3
        deck.readFromFile("src/test/kotlin/TestDeckInput.txt")
        val jimmy = Player("Jimmy", false)
        jimmy.draw(deck)
        assertEquals(26, jimmy.calculateHandScore())
        assertEquals(3, jimmy.getHand().size)
    }

    @Test
    fun testShouldNotDrawWhenBlackjack() {
        val goodHand = arrayListOf<Card>(
            Card(11, "C", "CA"),
            Card(10, "C", "CK")
        )
        val jimmy = Player("Jimmy", false)
        for (card in goodHand) jimmy.handCard(card)
        jimmy.draw(Deck())
        assertEquals(21, jimmy.calculateHandScore())
        assertEquals(2, jimmy.getHand().size)
    }

    @Test
    fun testShouldNotDrawWhenScoreIsMax() {
        val okHand = arrayListOf<Card>(
            Card(10, "C", "CJ"),
            Card(8, "C", "C8")
        )
        val jimmy = Player("Jimmy", false)
        for (card in okHand) jimmy.handCard(card)
        jimmy.draw(Deck())
        assertEquals(18, jimmy.calculateHandScore())
        assertEquals(2, jimmy.getHand().size)
    }

    @Test
    fun testShouldNotDrawWhenScoreIsGivenMax() {
        val maxSum: Int = 19
        val okHand = arrayListOf<Card>(
            Card(10, "C", "CJ"),
            Card(10, "C", "CQ")
        )
        val jimmy = Player("Jimmy", false)
        for (card in okHand) jimmy.handCard(card)
        jimmy.draw(Deck(), maxSum)
        assertEquals(20, jimmy.calculateHandScore())
        assertEquals(2, jimmy.getHand().size)
    }

    @Test
    fun testGetName() {
        val jimmy = Player("Jimmy", false)
        assertEquals("Jimmy", jimmy.name)
    }

    @Test
    fun testIsDealer() {
        val jimmy = Player("Jimmy", true)
        assertEquals(true, jimmy.isDealer)
    }

    @Test
    fun testPrintHand() {
        val okHand = arrayListOf<Card>(
            Card(10, "C", "CJ"),
            Card(10, "C", "CQ")
        )
        val jimmy = Player("Jimmy", true)
        for (card in okHand) jimmy.handCard(card)
        val output = tapSystemOut {
            jimmy.printHand()
        }

        assertEquals("Jimmy: CJ, CQ\n", output)
    }
}