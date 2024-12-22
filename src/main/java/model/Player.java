package model;

import java.util.*;
import lombok.Data;
import model.Card.Suit;

public @Data class Player {
    private String playerName;
    private int playerId;
    private int playerScore;
    private List<Card> playerHand;
    private Map<Suit, Integer> suitValues;
    private boolean isComputer;

    public Player() {
        playerName = "Player";
        suitValues = new EnumMap<>(Suit.class);
    }

    public void addCardToHand(Card card) {
        if(this.playerHand == null) {
            this.playerHand = new ArrayList<>();
        }
        this.playerHand.add(card);
        updateSuitValues();
    }

    public void removeCardFromHand(int index) {
        this.playerHand.remove(index);
        updateSuitValues();
    }

    private void updateSuitValues() {
        suitValues.clear();
        for (Card card : playerHand) {
            Suit suit = card.suit();
            int cardValue = card.rank();
            if (card.rank() == 1) {
                int currentSuitValue = suitValues.getOrDefault(suit, 0);

                if (currentSuitValue + 11 <= 21) {
                    cardValue = 11;
                }
            }
            suitValues.put(suit, suitValues.getOrDefault(suit, 0) + cardValue);
        }
    }


    public void generateHand(List<Card> deck) {
        if (playerHand == null) {
            playerHand = new ArrayList<>();
        }
        playerHand.clear();
        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.removeFirst());
        }
        updateSuitValues();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId == player.playerId && playerName.equals(player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, playerName);
    }

    @Override
    public String toString() {
         return "Player " + playerId + ", " + playerName + ":\n" +
                "Suit Values:\n" +
                suitValues.toString() +
                "\nHand: " + playerHand;
    }
}