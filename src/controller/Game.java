package controller;

import model.Card;
import model.Player;

import java.util.*;

public class Game {
    private Scanner sc;
    private List<Player> players;
    private List<Card> deck;
    private Set<Player> playersWith21Hands;
    private PlayerActions playerAction;

    public Game() {
        sc = new Scanner(System.in);
        deck = Card.getStandardShuffledDeck();
        players = Player.generatePlayers(sc, getAmountOfPlayers(), deck);
        playersWith21Hands = new HashSet<>();
        playerAction = new PlayerActions(sc);
    }

    public void playGame() {
        while (gameNotOver()) {
            action();
        }
        System.out.println(gameOverType());
    }

    private void action() {
        for (Player player : players) {
            System.out.println("-".repeat(30) + "\n" + player + "\n" + "-".repeat(30));
            playerAction.dropCard(player);
            if (player.getHandValue() == 21) {
                playersWith21Hands.add(player);
            }
            playerAction.drawCard(player, deck);
            if (player.getHandValue() == 21) {
                playersWith21Hands.add(player);
            }
        }
    }

    public boolean gameNotOver() {
        return playersWith21Hands.isEmpty() && !deck.isEmpty();
    }

    public String gameOverType() {
        if (playersWith21Hands.size() > 1) {
            return "Game over! Multiple players achieved 21, nobody gets a point!";
        } else if (playersWith21Hands.size() == 1) {
            return "Game over! \n" + playersWith21Hands + "\nscores a point!";
        } else {
            return "The deck is out of cards. No players have a deck with value of 21. It's a draw!";
        }
    }

    public int getAmountOfPlayers() {
        int playerNumber = -1;
        try {
            System.out.println("Enter number of players, (2-6):");
            playerNumber = Integer.parseInt(sc.nextLine());
            if (playerNumber < 2 || playerNumber > 6) {
                System.out.println("Invalid player number. Enter a number between 2 and 6.");
                return getAmountOfPlayers();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            return getAmountOfPlayers();
        }
        return playerNumber;
    }
}
