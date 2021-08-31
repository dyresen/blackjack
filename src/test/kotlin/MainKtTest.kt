import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MainKtTest {

    @Test
    fun testReadDeck() {
        val expected = mutableListOf("CA", "D5", "H9", "HQ", "S8")
        val testDeck = "testDeck.txt"
        assertEquals(expected, readDeck(testDeck))
    }

    @Test
    fun testGenerateDeck() {
        val deckSize = generateDeck().size
        val expected = 52
        assertEquals(expected, deckSize)
    }

    @Test
    fun dealCard() {
        val expected = mutableListOf("S8", "CA")
        val hand = mutableListOf("S8")
        val deck = mutableListOf("CA", "HQ")
        assertEquals(expected, dealCard(hand, deck))
    }

    @Test
    fun handValue() {
        val expected = 21
        val hand = mutableListOf("CA", "HQ")
        assertEquals(expected, handValue(hand))
    }

    @Test
    fun checkBlackJack() {
        val expected = true
        assertEquals(expected, checkBlackJack(21))
    }

    @Test
    fun busted() {
        val expected = true
        assertEquals(expected, busted(22))
    }

    @Test
    fun main() {
        val expected = "Sam\n" +
            "Sam: CA, H9\n" +
            "Dealer: D5, HQ, S8"
        assertEquals(
            expected,
            main(arrayOf("testDeck.txt"))
        )
    }
}
