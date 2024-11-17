package model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerScoreUpdate() {
        Player player = new Player();
        player.setPlayerScore(0);
        player.setPlayerName("Test Player");
        player.setPlayerScore(player.getPlayerScore() + 1);
        assertEquals(1, player.getPlayerScore(), "Player score should be incremented by 1");
    }
}
