package model;

import java.util.*;

public class Player {
    private String playerName;
    private int playerId;
    private List<Card> playerHand;
    private boolean isComputer;

    public Player() {
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
    }

    public void addCardToHand(Card card) {
        this.playerHand.add(card);
    }

    public void removeCardFromHand(int index) {
        this.playerHand.remove(index);
    }

    public int getHandValue() {
        int deckValue = 0;
        boolean hasAce = false;


        for (var card : playerHand) {
            deckValue += card.rank();
            if (card.face().charAt(0) == 'A') {
                hasAce = true;
            }
        }

        if (hasAce && deckValue + 10 <= 21) {
            deckValue += 10;
        }
        return deckValue;
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
        return
                 "Player " + playerId + ", " + playerName + ":\n" + "Deck value: " + getHandValue()
                        + "\nDeck: " + playerHand;
    }
}
