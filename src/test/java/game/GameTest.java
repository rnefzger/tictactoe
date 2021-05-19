package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Game gameVSCom;

    @BeforeEach
    void init(){
        game = new Game(false);
        gameVSCom = new Game(true);
    }

    @Test
    void nextTurn() {
        game.nextTurn();

        assertEquals(1, game.getTurnCounter());
    }

    @Test
    void nextTurnModulo2() {
        gameVSCom.nextTurn();
        gameVSCom.nextTurn();

        assertEquals(0, gameVSCom.getTurnCounter());
    }

    @Test
    void conquer() {
        game.conquer(0);
        game.conquer(8);

        assertEquals("[X][ ][ ]\n[ ][ ][ ]\n[ ][ ][O]", game.toString());
        assertEquals(0, game.getTurnCounter());
    }

    @Test
    void conquerFilledField() {
        game.conquer(0);
        game.conquer(0);

        assertEquals("[X][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]", game.toString());
    }

    @Test
    void conquerFalseInput() {
        assertThrows(IllegalArgumentException.class, () -> game.conquer(-1));
        assertThrows(IllegalArgumentException.class, () -> game.conquer(9));
    }

    @Test
    void finished1() {
        game.conquer(0);
        game.conquer(3);
        game.conquer(1);
        game.conquer(4);
        game.conquer(2);

        assertEquals(new Human(0), game.getWinner());
    }

    @Test
    void finished2() {
        game.conquer(7);
        game.conquer(3);
        game.conquer(8);
        game.conquer(4);
        game.conquer(6);
        game.conquer(5);

        assertEquals(new Human(1), game.getWinner());
    }

    @Test
    void gameOver() {
        game.conquer(0);
        game.conquer(1);
        game.conquer(2);
        game.conquer(3);
        game.conquer(4);
        game.conquer(5);
        game.conquer(6);
        game.conquer(7);
        game.conquer(8);

        assertTrue(game.gameOver());
    }

    @Test
    void testToString() {
        assertEquals("[ ][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]", game.toString());
    }
}