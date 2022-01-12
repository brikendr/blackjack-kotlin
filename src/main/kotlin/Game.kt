import models.Deck
import models.Player

class Game(private val dealer: Player, private val player: Player) {
    private val deck: Deck = Deck()

    fun initDeckFromFile(pathToFile: String) {
        deck.readFromFile(pathToFile)
        deck.printDeck()
    }

    fun play() {
        /* Initial step is to hand two cards to each player starting with the player first */
        if (deck.cardsInDeck() > 4) {
            player.handCard(deck.drawCard()!!)
            dealer.handCard(deck.drawCard()!!)
            player.handCard(deck.drawCard()!!)
            dealer.handCard(deck.drawCard()!!)
        }

        /*
        println("Initial hand dealt")
        println("${player.name} has an initial score of ${player.calculateHandScore()}")
        println("${dealer.name} has an initial score of ${dealer.calculateHandScore()}")
        */

        // Check if initial hand is blackjack
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            // Sam wins
            println("Sam")
        } else if (!player.isBlackjack() && dealer.isBlackjack()) {
            // Dealer wins
            println("Dealer")
        } else if (player.isBlackjack() && dealer.isBlackjack()) {
            // Sam wins
            println("Sam")
        } else if(player.calculateHandScore() == 22 && player.calculateHandScore() == 22) {
            // Dealer wins
            println("Dealer")
        } else {
            player.draw(deck)
            if (player.calculateHandScore() > 21) {
                // Dealer wins
                println("Dealer")
            } else {
                dealer.draw(deck, player.calculateHandScore())
                if (dealer.calculateHandScore() > 21) {
                    // Sam wins
                    println("Sam")
                } else {
                    // Highest score wins
                    println(if (player.calculateHandScore() > dealer.calculateHandScore()) "Sam" else "Dealer")
                }
            }
        }

        // Print hand
        player.printHand()
        dealer.printHand()
        // println("${player.name} has a final score of ${player.calculateHandScore()}")
        // println("${dealer.name} has a final score of ${dealer.calculateHandScore()}")
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