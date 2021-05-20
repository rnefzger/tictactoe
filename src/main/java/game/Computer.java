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
     * Carry out a turn of the computer
     *
     * @return the index to conquer
     */
    @Override
    public int turn() {
        return new Random().nextInt(8);
    }
}
