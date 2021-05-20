package game;

/**
 * Represents a human
 */
public class Human extends Player {

    Human(int id) {
        super(id);
    }

    /**
     * Carry out a turn of this human
     *
     * @param index to be conquered
     * @return the index to conquer
     */
    @Override
    public int turn(final int index) {
        return index - 1;
    }
}
