package models

class Player (val name: String, val isDealer: Boolean) :  Playable {
    private var hand: ArrayList<Card> = arrayListOf()

    fun handCard(card: Card) {
        // println("${name} draws ${card.suit}${card.label}")
        hand.add(card)
    }

    fun calculateHandScore(): Int {
        var score = 0
        for (card in hand) {
            score += card.score
        }
        return score
    }

    fun printHand() {
        var handStr: String = "$name: "
        for (card in hand) {
            handStr = handStr + "${card.label}, "
        }
        print(handStr.substring(0, handStr.length - 2))
        println()
    }

    fun getHand(): ArrayList<Card> {
        return hand
    }

    fun reset() {
        hand.clear()
    }

    override fun hasBlackjack(): Boolean {
        var hasAce = false
        var hasTenner = false
        for (card in hand) {
            if (card.score == 11) {
                hasAce = true
            } else if (card.score == 10) {
                hasTenner = true
                if (hasTenner && hasAce) break
            }
        }
        return hasAce && hasTenner
    }

    fun draw(deck: Deck, maxSum: Int = 17) {
        var canDraw: Boolean = !hasBlackjack() && calculateHandScore() <= maxSum
        while (canDraw) {
            var card: Card? = deck.drawCard()
            if (card != null) {
                // println("${name} draws ${card.suit}${card.label}")
                hand.add(card)
                val score = calculateHandScore()
                if (score > maxSum) break
            } else { break }
        }

    }
}