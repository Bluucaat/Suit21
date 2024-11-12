package controller;

import model.Card;
import model.Player;

import java.util.*;

public class Game {
    private Scanner sc;
    private List<Player> players;
    private List<Card> deck;
    private List<Player> playersWith21Hands;
    private PlayerActions playerAction;

    public Game() {
        sc = new Scanner(System.in);
        deck = Card.getStandardShuffledDeck();
        players = Player.generatePlayers(sc, getAmountOfPlayers(), deck);
        playersWith21Hands = new ArrayList<>();
        playerAction = new PlayerActions(sc);
    }

    public void playGame() {
        int amountOfGames = getAmountOfGames();
        for (int i = 0; i < amountOfGames; i++) {
            System.out.println("Game number " + amountOfGames);
            playersWith21Hands.clear();
            deck = Card.getStandardShuffledDeck();
            while (gameNotOver()) {
                action();
            }
            System.out.println(gameOverType());
            printPlayerScoreBoard();
        }

    }

    private void action() {
        for (Player player : players) {
            System.out.println("-".repeat(30) + "\n" + player + "\n" + "-".repeat(30));
            playerAction.dropCard(player);
            if (isPlayerWinner(player)) {
                playersWith21Hands.add(player);
                player.setPlayerScore(player.getPlayerScore() + 1);
                return;
            }
            playerAction.drawCard(player, deck);
            if (isPlayerWinner(player)) {
                playersWith21Hands.add(player);
                player.setPlayerScore(player.getPlayerScore() + 1);
                return;
            }
        }
    }

    private boolean isPlayerWinner(Player player) {
        Map<Card.Suit, Integer> suitValues = player.getSuitValues();

        for (Integer value : suitValues.values()) {
            if (value == 21) {
                return true;
            }
        }
        return false;
    }

    public boolean gameNotOver() {
        return playersWith21Hands.isEmpty() && !deck.isEmpty();
    }

    public String gameOverType() {
        if (playersWith21Hands.size() > 1) {
            return "Game over! Multiple players achieved 21 in a suit, nobody gets a point!";

        } else if (playersWith21Hands.size() == 1) {
            return "Game over! \n" + playersWith21Hands.getFirst() + "\nscores a point!";
        } else {
            return "The deck is out of cards. No players have a suit with value of 21. It's a draw!";
        }
    }

    public void printPlayerScoreBoard() {
        Collections.sort(players, Comparator.comparing(Player::getPlayerScore).reversed());
        System.out.println("-".repeat(30));
        System.out.println("Game results");
        System.out.println("-".repeat(30));
        for (Player player : players) {

            System.out.println("Player " +
                    player.getPlayerId() + ", " + player.getPlayerName() +
                    ": " + player.getPlayerScore());
        }
        System.out.println("-".repeat(30));
    }

    public int getAmountOfGames() {
        int amount;
        try {
            System.out.println("Enter a number of games to be played!");
            amount = Integer.parseInt(sc.nextLine());
            if (amount > 10 || amount < 1) {
                System.out.println("Invalid amount of games entered. enter a number between 1-10");
                return getAmountOfGames();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount of games entered. enter a number between 1-10");
            return getAmountOfGames();
        }
        return amount;
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
