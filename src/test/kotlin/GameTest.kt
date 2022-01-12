import models.Deck
import models.Player
import org.junit.jupiter.api.Test
import com.github.stefanbirkner.systemlambda.SystemLambda.*
import models.Card
import org.junit.jupiter.api.Assertions
import kotlin.test.assertEquals

internal class GameTest {

    private val player = Player("Jimmy", false)
    private val dealer = Player("Dealer", true)

    @Test
    fun testInitDeckFromFile() {
        val blackjack = Game(dealer, player)
        val stdOut = tapSystemOut {
            blackjack.initDeckFromFile("src/test/kotlin/TestDeckInput.txt")
        }
        Assertions.assertEquals("[C10, S6, SQ, D10, DK, HA, DJ, C5, D6, S3]\n", stdOut)
    }

    @Test
    fun testResetGame() {
        var card1 = Card(11, "C", "A")
        var card2 = Card(10, "D", "K")
        player.handCard(card1)
        dealer.handCard(card2)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        assertEquals(0, player.getHand().size)
        assertEquals(0, dealer.getHand().size)
    }

    @Test
    fun testPlayWhenPlayerHasBlackjackOnInitialHand() {
        val deckSequence = "DA, C4, SK, S10"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("Jimmy\n" +
                    "Jimmy: DA, SK\n" +
                    "Dealer: C4, S10\n", stdOut)
    }

    @Test
    fun testPlayWhenDealerHasBlackjackOnInitialHand() {
        val deckSequence = "C4, DA, SK, S10"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("Dealer\n" +
                "Jimmy: C4, SK\n" +
                "Dealer: DA, S10\n", stdOut)
    }

    @Test
    fun testPlayWhenBothHaveBlackjackOnInitialHand() {
        val deckSequence = "CA, DA, HK, SQ"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("Jimmy\n" +
                "Jimmy: CA, HK\n" +
                "Dealer: DA, SQ\n", stdOut)
    }

    @Test
    fun testPlayWhenBothScoresAre22() {
        val deckSequence = "CA, DA, HA, SA"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("Dealer\n" +
                "Jimmy: CA, HA\n" +
                "Dealer: DA, SA\n", stdOut)
    }

    @Test
    fun testPlayWhenPlayerDrawsOver21() {
        val deckSequence = "C10, D5, D5, S10, S8"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("Dealer\n" +
                "Jimmy: C10, D5, S8\n" +
                "Dealer: D5, S10\n", stdOut)
    }

    @Test
    fun testPlayWhenDealerDrawsOver21() {
        val deckSequence = "D5, C10, S10, S5, D3, SQ"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("Jimmy\n" +
                "Jimmy: D5, S10, D3\n" +
                "Dealer: C10, S5, SQ\n", stdOut)
    }

    @Test
    fun testPlayWhenDealerDrawsBelow21ButOverMaxPlayerScore() {
        val deckSequence = "D5, H5, C5, S5, C8, H9"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("Dealer\n" +
                "Jimmy: D5, C5, C8\n" +
                "Dealer: H5, S5, H9\n", stdOut)
    }

    @Test
    fun testPlayEdgeCaseWhenDeckIsShortAndHightScoreDeterminsWinner() {
        val deckSequence = "D5, H5, C5, S5, C8"
        val deck = Deck()
        deck.readFromStr(deckSequence)
        val blackjack = Game(dealer, player)
        blackjack.resetGame()
        blackjack.initGameWithDeck(deck)
        val stdOut = tapSystemOut {
            blackjack.play()
        }
        assertEquals("No more elements to draw from deck!\n" +
                "Jimmy\n" +
                "Jimmy: D5, C5, C8\n" +
                "Dealer: H5, S5\n", stdOut)
    }
}