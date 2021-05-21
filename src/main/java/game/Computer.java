package game;

import java.util.Random;

/**
 * Represents a computer
 */
public class Computer extends Player {

    /**
     * Highest number to generate (exclusive)
     */
    private static final int MAX_NUMBER = 9;

    Computer(final int id) {
        super(id);
    }

    /**
     * Calculates the index to conquer
     *
     * @return the index to conquer
     */
    @Override
    public int conquer() {
        return new Random().nextInt(MAX_NUMBER);
    }
}
