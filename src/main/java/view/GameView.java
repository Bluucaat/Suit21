package view;

import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameView {

    private final Scanner scanner;

    public GameView() {
        this.scanner = new Scanner(System.in);
    }

    public int getAmountOfPlayers() {
        while (true) {
            try {
                System.out.print("Enter a number of players! (2-6): ");
                int playerNumber = Integer.parseInt(scanner.nextLine());
                if (playerNumber >= 2 && playerNumber <= 6) {
                    return playerNumber;
                }
                System.out.println("Invalid input. Enter a number between 2-6.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }

    public int getAmountOfGames() {
        while (true) {
            try {
                System.out.print("Enter a number of games to play! (1-10): ");
                int gameNumber = Integer.parseInt(scanner.nextLine());
                if (gameNumber >= 1 && gameNumber <= 10) {
                    return gameNumber;
                }
                System.out.println("Invalid input. Enter a number between 1-10.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }

    public void displayPlayerInfo(Player player) {
        System.out.println("-".repeat(30));
        System.out.println(player);
        System.out.println("-".repeat(30));
    }

    public void printGameResults(List<Player> players) {
        System.out.println("-".repeat(30));
        System.out.println("Game Results");
        System.out.println("-".repeat(30));
        for (Player player : players) {
            System.out.println("Player " + player.getPlayerName() + ": " + player.getPlayerScore() + " points");
        }
        System.out.println("-".repeat(30));
    }

    public void printGameOverType(String message) {
        System.out.println(message);
    }

    public void displayRoundStartMessage(int round) {
        System.out.println("Starting Round " + round + "!");
    }

    public  ArrayList<Player> generatePlayers(Scanner sc) {
        ArrayList<Player> players = new ArrayList<>();
        int amountOfPlayers = this.getAmountOfPlayers();
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
}


