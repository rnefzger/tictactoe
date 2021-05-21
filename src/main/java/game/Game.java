package game;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a game of tictactoe and its rules
 */
public class Game implements IGame {

    /**
     * Is used to set console Colors
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * Error messages
     */
    private static final String ERROR_FALSE_INDEX = ANSI_RED + "Dieses Feld existiert nicht" + ANSI_RESET;
    private static final String FIELD_ASSIGNED = ANSI_RED + "Dieses Feld ist bereits vergeben!" + ANSI_RESET;

    /**
     * Size of a printed line
     */
    private static final int LINE_SIZE = 3;
    /**
     * Offset to generate a row for results
     */
    private static final int OFFSET_ROW = 3;
    /**
     * Represents the maximum size of fields
     */
    private static final int MAX_FIELD_SIZE = 9;

    /**
     * Storage of the fields
     */
    private final List<Field> fields;
    /**
     * Storage of the players
     */
    private final List<Player> players;
    /**
     * Storage of the results
     */
    private final List<List<Integer>> results;

    /**
     * Counter to calculate which player is to act
     */
    private int turnCounter;
    /**
     * The winner of the game
     */
    private Player winner;

    /**
     * Initializes a game.
     *
     * @param vsComputer true, if the game is vs a computer, otherwise false
     */
    public Game(final boolean vsComputer) {
        results = new ArrayList<>();

        //Adds all results to results
        for (int i = 0; i < 7; i += 3) {
            results.add(new ArrayList<>(List.of(i, i + 1, i + 2)));
        }
        for (int i = 0; i < 3; i++) {
            results.add(new ArrayList<>(List.of(i, i + OFFSET_ROW, i + 2*OFFSET_ROW)));
        }
        results.add(new ArrayList<>(List.of(0, 4, 8)));
        results.add(new ArrayList<>(List.of(2, 4, 6)));

        fields = new ArrayList<>();

        //Adds all fields to fields
        for (int i = 0; i < MAX_FIELD_SIZE; i++) {
            fields.add(new Field(i));
        }

        //Initializes players
        if (vsComputer) {
            players = new ArrayList<>(List.of(new Human(0), new Computer(1)));
        } else {
            players = new ArrayList<>(List.of(new Human(0), new Human(1)));
        }

        // new Random().nextInt(2)
        turnCounter = 0;
        // Sets a default winner
        winner = new Computer(2);

        fields.sort(Comparator.comparingInt(Field::getIndex));
    }

    /**
     * Calculates the next turn
     */
    @Override
    public void nextTurn() {
        turnCounter = ++turnCounter % 2;
    }

    /**
     * Let the current player take his turn
     *
     * @param forTests to set a fix index (only used for testing the method)
     * @return true, if the field got conquered, otherwise false
     */
    @Override
    public boolean turn(final Integer... forTests) {
        Player currentPlayer = players.get(turnCounter);
        Field field;

        try {
            if (forTests.length != 0) {
                field = fields.get(currentPlayer.conquer(forTests[0]));
            } else {
                field = fields.get(currentPlayer.conquer());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(ERROR_FALSE_INDEX);
            return false;
        }

        if (field.getBelongsTo().getId() != 2) {
            if (currentPlayer instanceof Human) {
                System.out.println(FIELD_ASSIGNED);
            }

            return false;
        }

        field.setFilled(true);
        field.setBelongsTo(currentPlayer);

        if (win()) {
            winner = currentPlayer;
        }

        nextTurn();
        return true;
    }

    /**
     * Checks if the game is completely filled
     *
     * @return true, if all fields are filled, otherwise false
     */
    @Override
    public boolean gameOver() {
        return  fields.stream().allMatch(Field::isFilled);
    }

    /**
     * Checks if the current player won the game
     *
     * @return true, if the player won the game, otherwise false
     */
    @Override
    public boolean win() {
        List<Integer> currentPlayerFields = fields.stream()
                .filter(x -> x.getBelongsTo().getId() == turnCounter)
                .map(Field::getIndex)
                .collect(Collectors.toList());

        return results.stream().anyMatch(currentPlayerFields::containsAll);
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Generates the matchfield
     *
     * @return the matchfield
     */
    @Override
    public String toString() {
        StringBuilder stats = new StringBuilder();

        fields.forEach(x -> {
            if (x.getIndex() % LINE_SIZE == 0 && x.getIndex() != 0) {
                stats.append("\n");
            }

            stats.append(x);
        });

        return stats.toString();
    }
}
