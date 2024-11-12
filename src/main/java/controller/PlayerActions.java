package controller;

import model.Card;
import model.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PlayerActions {

    private final Scanner sc;

    public PlayerActions(Scanner sc) {
        this.sc = sc;
    }

    public void dropCard(Player player) {
        if (player.isComputer()) {
            Random r = new Random();
            int choice = r.nextInt(5);
            try {
                System.out.println("Computer is thinking... (really)");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error: Computer is too dumb.");
            }
            System.out.println("Computer dropped card: " + player.getPlayerHand().get(choice));
            player.removeCardFromHand(choice);

        } else {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Input an index of a card from your deck to drop out!");
                int index;

                try {
                    index = Integer.parseInt(sc.nextLine()) - 1;
                    if (index < 0 || index >= player.getPlayerHand().size()) {
                        System.out.println("Invalid index! Enter a number between 1-5\n");
                        continue;
                    }
                    System.out.println("Player dropped card: " + player.getPlayerHand().get(index));
                    player.removeCardFromHand(index);
                    System.out.println("Updated suit values after dropping: " + player.getSuitValues());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number! Enter a number between 1-5\n");
                }
            }
        }
    }

    public void drawCard(Player player, List<Card> deck) {
        if (!deck.isEmpty()) {
            Card drawnCard = deck.removeFirst();
            player.addCardToHand(drawnCard);
            System.out.println("New card handed out. Updated hand: " + player.getPlayerHand());
            System.out.println("Updated suit values after drawing: " + player.getSuitValues());
        }
    }
}
