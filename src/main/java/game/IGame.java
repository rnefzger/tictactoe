package game;

public interface IGame {

    void nextTurn();

    boolean turn(final Integer... index);

    boolean win();

    boolean gameOver();
}
