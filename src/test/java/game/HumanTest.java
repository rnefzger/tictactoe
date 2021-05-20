package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void turn() {
        Player player = new Human(0);

        assertEquals(2, player.turn(3));
    }
}