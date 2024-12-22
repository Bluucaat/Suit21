import controller.GameController;
import controller.GameHandler;
import model.Player;
import view.GameView;

import java.util.List;
import java.util.Scanner;

public class GameApp {
    public static void main(String[] args) {

        GameView gameView = new GameView();
        List<Player> players = gameView.generatePlayers(new Scanner(System.in));
        GameController gameController = new GameController(players);
        GameHandler gameHandler = new GameHandler(gameController, gameView);

        gameHandler.play(gameView.getAmountOfGames());
    }
}
