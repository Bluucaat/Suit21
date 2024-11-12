package model;

import java.util.*;

import model.Card.Suit;

public class Player {
    private String playerName;
    private int playerId;
    private int playerScore;
    private List<Card> playerHand;
    private Map<Suit, Integer> suitValues;
    private boolean isComputer;

    public Player() {
        suitValues = new EnumMap<>(Suit.class);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
        updateSuitValues();
    }

    public void addCardToHand(Card card) {
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
            suitValues.put(suit, suitValues.getOrDefault(suit, 0) + cardValue);
        }
    }

    public Map<Suit, Integer> getSuitValues() {
        return suitValues;
    }

    public static ArrayList<Player> generatePlayers(Scanner sc, int amountOfPlayers, List<Card> deck) {
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 1; i < amountOfPlayers + 1; i++) {
            System.out.println("Enter player name: ");
            String playerName = sc.nextLine();
            Player player = new Player();
            player.setPlayerId(i);
            player.setPlayerName(playerName);
            player.setPlayerHand(generateHand(deck));
            player.setComputer(playerName.equalsIgnoreCase("Computer"));
            players.add(player);
        }
        return players;
    }

    public static List<Card> generateHand(List<Card> deck) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand.add(deck.removeFirst());
        }
        return hand;
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