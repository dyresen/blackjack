import java.io.File
import java.io.IOException
import kotlin.system.exitProcess

fun readDeck(fileName: String): MutableList<String> {
    val filecontent = try {
        File(fileName).bufferedReader().readLine().split(",").toMutableList()
    } catch (e: IOException) {
        println("Provided file doesn't exist, good bye!")
        exitProcess(0)
    }

    // Lets trim all white space from list entries before returning it. It makes life easier.
    val deck = filecontent.map { it.trim() }
    return deck.toMutableList()
}

fun generateDeck(): MutableList<String> {
    val cardColors = arrayOf("C", "D", "H", "S")
    val cardValues = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K")
    val deck = mutableListOf<String>()
    for (cardColor in cardColors) {
        // deck.add(cardValue)

        for (cardValue in cardValues) {
            deck.add(cardColor + cardValue)
        }
    }

    // Lets shuffle the deck before returning it, to make this a fair game
    deck.shuffle()
    return deck
}

fun dealCard(hand: MutableList<String>, deck: MutableList<String>): MutableList<String> {
    hand.add(deck.removeFirst())
    return hand
}

fun handValue(hand: MutableList<String>): Int {
    var total = 0
    for (item in hand) {
        when (item.drop(1)) {
            "A" -> total += 11
            "J" -> total += 10
            "Q" -> total += 10
            "K" -> total += 10
            "2" -> total += 2
            "3" -> total += 3
            "4" -> total += 4
            "5" -> total += 5
            "6" -> total += 6
            "7" -> total += 7
            "8" -> total += 8
            "9" -> total += 9
            "10" -> total += 10
        }
    }
    return total
}

fun checkBlackJack(score: Int): Boolean {
    if (score == 21) {
        return true
    }
    return false
}

fun busted(score: Int): Boolean {
    if (score > 21) {
        return true
    }
    return false
}

fun endGame(winner: String, samHand: MutableList<String>, dealerHand: MutableList<String>): String {
    val separator = ", "
    println(winner)
    println("Sam: ${samHand.joinToString(separator)}")
    println("Dealer: ${dealerHand.joinToString(separator)}")
    exitProcess(0)
}

fun main(args: Array<String>) {
    val deck = if (args.isNotEmpty()) readDeck(args[0]) else generateDeck()
    var samHand = mutableListOf<String>()
    var dealerHand = mutableListOf<String>()

    // Deal cards to both players, to start the game
    repeat(2) {
        samHand = dealCard(samHand, deck)
        dealerHand = dealCard(dealerHand, deck)
    }

    // Calculate inital score
    var samScore: Int
    var dealerScore: Int
    samScore = handValue(samHand)
    dealerScore = handValue(dealerHand)

    // Check if either player has blackjack. If Sam has blackjack, he winns the game.
    if (checkBlackJack(samScore)) {
        println("Det er blackJack til Sam")
        endGame("Sam", samHand, dealerHand)
    }

    // If we are here, Sam Doesn't have blackjack. If the dealer does, we can end here.
    if (checkBlackJack(dealerScore)) {
        println("Det er blackJack til dealer")
        endGame("dealer", samHand, dealerHand)
    }

    // Dealer wins when both players starts with 22 (A + A)
    if (samScore == 22 && dealerScore == 22) {
        endGame("Dealer", samHand, dealerHand)
    }

    // keep drawing cards until player has 17 or more
    while (samScore < 17) {
        samHand = dealCard(samHand, deck)
        samScore = handValue(samHand)
    }

    // Sam has lost the game if their total is higher than 21
    if (busted(samScore)) {
        endGame("Dealer", samHand, dealerHand)
    }

    // Dealer draws cards until value is higher than sam or busted
    while (dealerScore <= samScore) {
        dealerHand = dealCard(dealerHand, deck)
        dealerScore = handValue(dealerHand)
    }

    // The dealer has lost the game if their total is higher than 21
    if (busted(dealerScore)) {
        endGame("Sam", samHand, dealerHand)
    }

    // Check score and return status
    if (samScore <= 21 && dealerScore >= 22) {
        endGame("Sam", samHand, dealerHand)
    }

    if (samScore > dealerScore) {
        endGame("Sam", samHand, dealerHand)
    } else {
        endGame("Dealer", samHand, dealerHand)
    }

    println("samscore: $samScore")
    println("dealerscore: $dealerScore")
}
