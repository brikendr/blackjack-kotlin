import models.Deck
import models.Player

class Game(private val dealer: Player, private val player: Player) {
    private var deck: Deck = Deck()

    fun initDeckFromFile(pathToFile: String) {
        deck.readFromFile(pathToFile)
        deck.printDeck()
    }

    fun initGameWithDeck(deck: Deck) {
        this.deck = deck
    }

    fun play() {
        /* Initial step is to hand two cards to each player starting with the player first */
        if (deck.cardsInDeck() >= 4) {
            player.handCard(deck.drawCard()!!)
            dealer.handCard(deck.drawCard()!!)
            player.handCard(deck.drawCard()!!)
            dealer.handCard(deck.drawCard()!!)
        }
        // Check if initial hand is blackjack
        if (player.hasBlackjack() && !dealer.hasBlackjack()) {
            // Player wins
            println(player.name)
        } else if (!player.hasBlackjack() && dealer.hasBlackjack()) {
            // Dealer wins
            println(dealer.name)
        } else if (player.hasBlackjack() && dealer.hasBlackjack()) {
            // Player wins
            println(player.name)
        } else if(player.calculateHandScore() == 22 && player.calculateHandScore() == 22) {
            // Dealer wins
            println(dealer.name)
        } else {
            player.draw(deck)
            if (player.calculateHandScore() > 21) {
                // Dealer wins
                println(dealer.name)
            } else {
                dealer.draw(deck, player.calculateHandScore())
                if (dealer.calculateHandScore() > 21) {
                    // Player wins
                    println(player.name)
                } else {
                    // High score wins
                    println(if (player.calculateHandScore() > dealer.calculateHandScore()) player.name else dealer.name)
                }
            }
        }

        // Print hand
        player.printHand()
        dealer.printHand()
    }

    fun resetGame() {
        deck.clear()
        deck.populateDeck()
        player.reset()
        dealer.reset()
    }
}

fun main(args: Array<String>) {
    val sam: Player = Player("Sam", false)
    val dealer: Player = Player("Dealer", true)
    val blackjack: Game = Game(player = sam, dealer = dealer)
    blackjack.play()
}