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

    public void play(int roundAmount) {
        for (int i = 0; i < roundAmount; i++) {
            view.displayRoundStartMessage(i + 1);
            gameController.startGame();
            while (gameController.gameOngoing()) {
                for (Player player : gameController.getPlayers()) {
                    view.displayPlayerInfo(player);
                    gameController.action(player);
                }
            }
            view.printGameOverType(gameController.getGameOverMessage());
            view.printGameResults(gameController.getPlayers());
        }
    }
}
