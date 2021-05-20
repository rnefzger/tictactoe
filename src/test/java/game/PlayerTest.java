package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Player samePlayer;

    @BeforeEach
    void init() {
        player = new Player(0) {
            @Override
            public int conquer() {
                return 0;
            }
        };
        samePlayer = new Player(0) {
            @Override
            public int conquer() {
                return 0;
            }
        };
    }

    @Test
    void testHashCode() {
        assertEquals(player.hashCode(), samePlayer.hashCode());
    }

}