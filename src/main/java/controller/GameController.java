package controller;

import lombok.Getter;
import lombok.Setter;
import model.Card;
import model.Player;
import view.PlayerActionsView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    @Getter
    private final List<Player> players;
    @Getter
    @Setter
    private List<Card> deck;
    @Getter
    private final List<Player> playersWith21Hands;
    private final PlayerActionsController playerAction;

    public GameController(List<Player> players) {
        this.playerAction = new PlayerActionsController(new PlayerActionsView());
        this.players = players;
        this.playersWith21Hands = new ArrayList<>();
    }

    public void startGame() {
        deck = Card.getStandardShuffledDeck();
        playersWith21Hands.clear();
        for (Player player : players) {
            player.generateHand(deck);
        }
    }

    public void action(Player player) {
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
        }
    }

    public boolean isPlayerWinner(Player player) {
        return player.getSuitValues().containsValue(21);
    }

    public boolean gameOngoing() {
        return playersWith21Hands.isEmpty() && !deck.isEmpty();
    }

    public String getGameOverMessage() {
        if (playersWith21Hands.size() > 1) {
            return "Game over! Multiple players achieved 21 in a suit, nobody gets a point!";
        } else if (playersWith21Hands.size() == 1) {
            return "Game over! \n" + playersWith21Hands.getFirst().getPlayerName() + " scores a point!";
        } else {
            return "The deck is out of cards. No players have a suit with value of 21. It's a draw!";
        }
    }



}
