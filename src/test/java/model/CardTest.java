package model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void testCardComparison() {
        Card card1 = new Card(Card.Suit.HEART, "10", 10);
        Card card2 = new Card(Card.Suit.SPADE, "7", 7);

        assertTrue(card1.compareTo(card2) > 0, "Card with higher value should compare greater");
        assertTrue(card2.compareTo(card1) < 0, "Card with lower value should compare smaller");
    }

    @Test
    public void testDeckInitialization() {
        List<Card> deck = Card.getStandardShuffledDeck();
        assertEquals(52, deck.size(), "A standard deck should contain 52 cards");
        long uniqueCards = deck.stream().distinct().count();
        assertEquals(52, uniqueCards, "Deck should not have duplicate cards");
    }

    @Test
    public void testCardDrawFromDeck() {
        List<Card> deck = Card.getStandardShuffledDeck();
        deck.removeFirst();
        assertEquals(51, deck.size(), "A card should be removed from the deck");
    }
}
