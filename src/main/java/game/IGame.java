package game;

/**
 * Interface for games
 */
public interface IGame {

    /**
     * Calculates the next turn
     */
    void nextTurn();

    /**
     * Let the current player take his turn
     *
     * @param forTests to set a fix index (only used for testing the method)
     * @return true, if the field got conquered, otherwise false
     */
    boolean turn(final Integer... forTests);

    /**
     * Checks if the current player won the game
     *
     * @return true, if the player won the game, otherwise false
     */
    boolean win();

    /**
     * Checks if the game is completely filled
     *
     * @return true, if all fields are filled, otherwise false
     */
    boolean gameOver();
}
