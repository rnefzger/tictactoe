package game;

import java.util.*;
import java.util.stream.Collectors;

public class Game implements IGame {

    private static final int HIGHEST_INDEX = 8;
    private static final int LINE_SIZE = 3;

    private final List<Field> fields;
    private final List<Player> players;
    private final List<List<Integer>> results = new ArrayList(
            List.of(new ArrayList<>(List.of(0, 1, 2)), new ArrayList<>(List.of(3, 4, 5)), new ArrayList<>(List.of(6, 7, 8)),
                    new ArrayList<>(List.of(0, 3, 6)), new ArrayList<>(List.of(1, 4, 7)), new ArrayList<>(List.of(2, 5, 8)),
                    new ArrayList<>(List.of(0, 4, 8)), new ArrayList<>(List.of(2, 4, 6))));

    private int turnCounter;
    private Player winner = null;

    Game() {
        fields = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            fields.add(new Field(i));
        }

        players = new ArrayList<>(List.of(new Player(0), new Player(1)));
        turnCounter = 0;

        fields.sort(Comparator.comparingInt(Field::getIndex));
    }

    @Override
    public void nextTurn() {
        turnCounter = ++turnCounter % 2;
    }

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

        if (finished()) {
            winner = players.get(turnCounter);
        }

        nextTurn();
        return true;
    }

    @Override
    public boolean finished() {
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

    @Override
    public String toString() {
//        String[] string = fields.stream()
//                .map(x -> x.getBelongsTo().toString())
//                .collect(Collectors.joining("][", "[", "]")).split();
//
//        return String.format("%s\n%s\n%s", string[0], string[1], string[2]);

        StringBuilder stats = new StringBuilder();

        fields.forEach(x -> {
            if (x.getIndex() % LINE_SIZE == 0 && x.getIndex() != 0) {
                stats.append("\n");
            }

            stats.append(x.getBelongsTo());
        });

        return stats.toString();
    }
}
