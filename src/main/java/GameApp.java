import controller.GameController;
import controller.GameHandler;
import model.Player;
import view.GameView;

import java.util.List;
import java.util.Scanner;

public class GameApp {
    public static void main(String[] args) {

        GameView gameView = new GameView();

        int playerCount = gameView.getAmountOfPlayers();
        int roundCount = gameView.getAmountOfGames();

        List<Player> players = Player.generatePlayers(new Scanner(System.in), playerCount);
        GameController gameController = new GameController(players);
        GameHandler gameHandler = new GameHandler(gameController, gameView);

        gameHandler.startGame(roundCount);
    }
}
