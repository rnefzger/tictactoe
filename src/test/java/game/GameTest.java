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
    void getPlayers() {
        assertEquals(2, game.getPlayers().size());
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
        game.turn(0);
        game.turn(8);

        assertEquals("[X][ ][ ]\n[ ][ ][ ]\n[ ][ ][O]", game.toString());
        assertEquals(0, game.getTurnCounter());
    }

    @Test
    void conquerFilledField() {
        game.turn(0);
        game.turn(0);

        assertEquals("[X][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]", game.toString());
    }

    @Test
    void conquerFalseInput() {
        assertFalse(game.turn(-1));
        assertFalse(game.turn(9));
    }

    @Test
    void finished1() {
        game.turn(0);
        game.turn(3);
        game.turn(1);
        game.turn(4);
        game.turn(2);

        assertEquals(new Human(0), game.getWinner());
    }

    @Test
    void finished2() {
        game.turn(7);
        game.turn(3);
        game.turn(8);
        game.turn(4);
        game.turn(6);
        game.turn(5);

        assertEquals(new Human(1), game.getWinner());
    }

    @Test
    void gameOver() {
        game.turn(0);
        game.turn(1);
        game.turn(2);
        game.turn(3);
        game.turn(4);
        game.turn(5);
        game.turn(6);
        game.turn(7);
        game.turn(8);

        assertTrue(game.gameOver());
    }

    @Test
    void testToString() {
        assertEquals("[ ][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]", game.toString());
    }
}