package controller;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import model.Card;
import model.Player;

import java.util.ArrayList;
import java.util.List;

class GameControllerTest {

    private GameController gameController;
    private List<Player> players;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        gameController = new GameController(players);
    }

    @Test
    void testStartGame() {
        gameController.startGame();
        assertNotNull(gameController.getDeck());
        for (Player player : players) {
            assertFalse(player.getPlayerHand().isEmpty());
        }
    }

    @Test
    void testIsPlayerWinner() {
        Player player = new Player();
        player.addCardToHand(new Card(Card.Suit.HEART, "A", 1));
        player.addCardToHand(new Card(Card.Suit.HEART, "10", 10));

        assertTrue(gameController.isPlayerWinner(player));
    }

    @Test
    void testGetGameOverMessage_SingleWinner() {
        gameController.getPlayersWith21Hands().add(players.getFirst());
        String message = gameController.getGameOverMessage();
        assertEquals("Game over! \nPlayer scores a point!", message);
    }

    @Test
    void testGetGameOverMessage_MultipleWinners() {
        gameController.getPlayersWith21Hands().add(players.get(0));
        gameController.getPlayersWith21Hands().add(players.get(1));
        String message = gameController.getGameOverMessage();

        assertEquals("Game over! Multiple players achieved 21 in a suit, nobody gets a point!", message);
    }

    @Test
    void testGetGameOverMessage_Draw() {
        gameController.setDeck(new ArrayList<>());
        String message = gameController.getGameOverMessage();

        assertEquals("The deck is out of cards. No players have a suit with value of 21. It's a draw!", message);
    }
}
