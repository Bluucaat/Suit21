package controller;

import model.Card;
import model.Player;
import view.PlayerActionsView;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlayerActionsController {
    private final PlayerActionsView playerActionsView;

    public PlayerActionsController(PlayerActionsView playerActionsView) {
        this.playerActionsView = playerActionsView;
    }

    public void dropCard(Player player) {
        int choice;
        if (player.isComputer()) {
            choice = getComputerChoice(player.getPlayerHand().size());
            playerActionsView.printMessage("Computer is thinking...");
            pauseForEffect();
            playerActionsView.printMessage("Computer dropped card: " + player.getPlayerHand().get(choice));
        } else {
            choice = playerActionsView.getCardIndexToDrop(player.getPlayerHand().size());
            playerActionsView.printMessage("Player dropped card: " + player.getPlayerHand().get(choice));
        }
        player.removeCardFromHand(choice);
    }

    public void drawCard(Player player, List<Card> deck) {
        if (!deck.isEmpty()) {
            Card drawnCard = deck.removeFirst();
            player.addCardToHand(drawnCard);
            playerActionsView.displayHand(player);
            playerActionsView.displaySuitValues(player);
        } else {
            playerActionsView.printMessage("Deck is empty.");
        }
    }

    int getComputerChoice(int handSize) {
        Random random = new Random();
        return random.nextInt(handSize);
    }

    private void pauseForEffect() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
