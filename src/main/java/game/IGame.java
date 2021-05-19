package game;

public interface IGame {

    void nextTurn();

    boolean conquer(final int index);

    boolean win();

    boolean gameOver();
}
