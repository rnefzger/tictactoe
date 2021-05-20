package game;

import java.util.Random;

/**
 * Represents a computer
 */
public class Computer extends Player {

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
        return new Random().nextInt(8);
    }
}
