package view;

import model.Player;

import java.util.Scanner;

public class PlayerActionsView {
    private final Scanner sc;

    public PlayerActionsView() {
        this.sc = new Scanner(System.in);
    }

    public int getCardIndexToDrop(int handSize) {
        int index = -1;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Input an index of a card from your hand to drop (1-" + handSize + "):");
            try {
                index = Integer.parseInt(sc.nextLine()) - 1;
                if (index < 0 || index >= handSize) {
                    System.out.println("Invalid index! Enter a valid index.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return index;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void displayHand(Player player) {
        System.out.println("Updated hand: " + player.getPlayerHand());
    }

    public void displaySuitValues(Player player) {
        System.out.println("Updated suit values: " + player.getSuitValues());
    }
}
