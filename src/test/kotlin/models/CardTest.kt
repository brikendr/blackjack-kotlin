package models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CardTest {

    @Test
    fun testToString() {
        val tenOfClubs: Card = Card(score = 10, suit = "C", label = "C10")
        assertEquals("C10", tenOfClubs.toString())
        val aceOfSpade: Card = Card(score = 11, suit = "S", label = "SA")
        assertEquals("SA", aceOfSpade.toString())
    }

    @Test
    fun testEquals() {
        val tenOfClubs: Card = Card(score = 10, suit = "C", label = "C10")
        val tenOfClubs2: Card = Card(score = 10, suit = "C", label = "C10")
        assertEquals(true, tenOfClubs.equals(tenOfClubs2))

        val aceOfSpade = Card(score=11, suit = "S", label = "SA")
        val aceOfSpade2 = Card(score=10, suit = "S", label = "SA")
        assertEquals(false, aceOfSpade.equals(aceOfSpade2))
    }

    @Test
    fun testParseScore() {
        assertEquals(11, Card.parseScore("A"))
        assertEquals(10, Card.parseScore("K"))
        assertEquals(10, Card.parseScore("Q"))
        assertEquals(10, Card.parseScore("J"))
        assertEquals(5, Card.parseScore("5"))
        assertEquals(0, Card.parseScore("L"))
    }
}