package controller;

import model.Player;
import view.GameView;

public class GameHandler {
    private final GameController gameController;
    private final GameView view;

    public GameHandler(GameController gameController, GameView view) {
        this.gameController = gameController;
        this.view = view;
    }

    public void startGame(int roundAmount) {
        for (int i = 0; i < roundAmount; i++) {
            view.displayRoundStartMessage(i + 1);
            gameController.startGame();
            while (gameController.gameNotOver()) {
                for (Player player : gameController.getPlayers()) {
                    view.displayPlayerInfo(player);
                    gameController.action(player, i); // Pass round count if necessary
                }
            }
            view.printGameOverType(gameController.getGameOverMessage());
            view.printGameResults(gameController.getPlayers());
        }
    }
}
