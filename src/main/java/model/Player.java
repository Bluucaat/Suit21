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


    public static ArrayList<Player> generatePlayers(Scanner sc, int amountOfPlayers) {
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 1; i < amountOfPlayers + 1; i++) {
            System.out.println("Enter player name: ");
            String playerName = sc.nextLine();
            Player player = new Player();
            player.setPlayerId(i);
            player.setPlayerName(playerName);
            player.setComputer(playerName.equalsIgnoreCase("Computer"));
            players.add(player);
        }
        return players;
    }

    public void generateHand(List<Card> deck) {
        if (playerHand == null) {
            playerHand = new ArrayList<>();
        }
        playerHand.clear();
        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.removeFirst());
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append("Player ").append(playerId).append(", ").append(playerName).append(":\n");

        sb.append("Suit Values:\n");
        int size = suitValues.size();
        int index = 0;
        for (Map.Entry<Suit, Integer> entry : suitValues.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue());
            if (index < size - 1) {
                sb.append(", ");
            }
            index++;
        }
        sb.append("\nHand: ").append(playerHand);
        return sb.toString();
    }
}