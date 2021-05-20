package game;

import java.util.Scanner;

/**
 * Represents a computer
 */
public class Computer extends Player {

    Computer(int id) {
        super(id);
    }

    /**
     * Carry out a turn of the computer
     *
     * @param index to be conquered
     * @return the index to conquer
     */
    //TODO
    @Override
    public int turn(final int index) {
        //TODO: Kann kleiner als 0 werden
        return (int) (Math.random() * 10 - 1);
    }
}
