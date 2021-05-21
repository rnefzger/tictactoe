package game;

import java.util.Scanner;

/**
 * Represents a human
 */
public class Human extends Player {

    /**
     * Highest index for a Field
     */
    private static final int HIGHEST_INDEX = 8;

    /**
     * Is used to scan the next input line
     */
    private final Scanner scanner;

    Human(final int id) {
        super(id);
        scanner = new Scanner(System.in);
    }

    /**
     * Calculates the index to conquer
     *
     * @return the index to conquer
     */
    @Override
    public int conquer() {
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index > HIGHEST_INDEX) {
            throw new IndexOutOfBoundsException();
        }

        return index;
    }
}
