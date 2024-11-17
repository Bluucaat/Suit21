package controller;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import model.Card;
import model.Player;
import view.PlayerActionsView;
import java.util.ArrayList;
import java.util.List;

class PlayerActionsControllerTest {

    private PlayerActionsController playerActionsController;
    private Player player;

    @Mock
    private PlayerActionsView mockView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        player = new Player();
        player.setPlayerName("Player1");
        player.setPlayerHand(new ArrayList<>());
        playerActionsController = new PlayerActionsController(mockView);
    }

    @Test
    void testDrawCard() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(Card.Suit.HEART, "A", 1));
        deck.add(new Card(Card.Suit.HEART, "10", 10));
        playerActionsController.drawCard(player, deck);
        assertEquals(1, player.getPlayerHand().size());
        assertTrue(player.getPlayerHand().contains(new Card(Card.Suit.HEART, "A", 1)));
    }

    @Test
    void testDropCard() {
        player.addCardToHand(new Card(Card.Suit.CLUB, "10", 10));
        player.addCardToHand(new Card(Card.Suit.DIAMOND, "A", 1));

        playerActionsController.dropCard(player);
        assertEquals(1, player.getPlayerHand().size());
        assertFalse(player.getPlayerHand().contains(new Card(Card.Suit.CLUB, "10", 10)));
    }

    @Test
    void testDropCardByComputer() {
        player.setComputer(true);
        player.addCardToHand(new Card(Card.Suit.CLUB, "10", 10));
        player.addCardToHand(new Card(Card.Suit.DIAMOND, "5", 5));
        playerActionsController.dropCard(player);
        assertEquals(1, player.getPlayerHand().size());
    }

    @Test
    void testGetComputerChoice() {
        int handSize = 3;
        int choice = playerActionsController.getComputerChoice(handSize);
        assertTrue(choice >= 0 && choice < handSize);
    }
}
