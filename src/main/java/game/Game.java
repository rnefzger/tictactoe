package game;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a game of tictactoe and its rules
 */
public class Game implements IGame {

    /**
     * Highest index for a Field
     */
    private static final int HIGHEST_INDEX = 8;
    /**
     * Size of a printed line
     */
    private static final int LINE_SIZE = 3;

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
    private final List<List<Integer>> results = new ArrayList(
            List.of(new ArrayList<>(List.of(0, 1, 2)), new ArrayList<>(List.of(3, 4, 5)), new ArrayList<>(List.of(6, 7, 8)),
                    new ArrayList<>(List.of(0, 3, 6)), new ArrayList<>(List.of(1, 4, 7)), new ArrayList<>(List.of(2, 5, 8)),
                    new ArrayList<>(List.of(0, 4, 8)), new ArrayList<>(List.of(2, 4, 6))));

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
        fields = new ArrayList<>();

        //Adds all fields to fields
        for (int i = 0; i < 9; i++) {
            fields.add(new Field(i));
        }

        //Initializes players
        if (vsComputer) {
            players = new ArrayList<>(List.of(new Human(0), new Computer(1)));
        } else {
            players = new ArrayList<>(List.of(new Human(0), new Human(1)));
        }

        turnCounter = 0;
        //Sets a default winner
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
     * Let the current player conquer the given field
     *
     * @param index field to be conquered
     * @return true, if the field got conquered, otherwise false
     */
    @Override
    public boolean conquer(int index) {
        if (index < 0 || index > HIGHEST_INDEX) {
            throw new IllegalArgumentException();
        }

        if (fields.get(index).getBelongsTo().getId() != 2) {
            return false;
        }

        Field field = fields.get(index);

        field.setFilled(true);
        field.setBelongsTo(players.get(turnCounter));

        if (win()) {
            winner = players.get(turnCounter);
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
