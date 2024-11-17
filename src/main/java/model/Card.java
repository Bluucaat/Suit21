package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Card(Suit suit, String face, int rank) implements Comparable<Card>{

    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()];
        }
    }

    @Override
    public String toString() {
        int cutIndex = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, cutIndex);
        return this.suit.getImage() + " " + faceString;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.rank, other.rank);
    }

    public static Card getNumbericCard(Suit suit, int rank) {
        return new Card(suit, String.valueOf(rank), rank);
    }

    private static Card getFaceCard(Suit suit, char abbr) {
        if(abbr == 'A'){
            return new Card(suit, String.valueOf(abbr), 1);
        }else
            return new Card(suit, String.valueOf(abbr), 10);
    }

    public static List<Card> getStandardShuffledDeck() {
        List<Card> standardDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 2; i < 11; i++) {
                standardDeck.add(getNumbericCard(suit, i));
            }
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                standardDeck.add(getFaceCard(suit, c));
            }
        }
        Collections.shuffle(standardDeck);
        return standardDeck;
    }
}
