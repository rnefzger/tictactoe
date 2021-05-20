package game;

import java.util.Objects;

/**
 * Represents a player in the game
 */
public abstract class Player {

    /**
     * The unique identifier of this player
     */
    private final int id;

    Player(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Carry out a turn of the current player
     *
     * @param index to be conquered
     * @return the index to conquer
     */
    public abstract int turn(final int index);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * The sign of the this player
     *
     * @return sign of this player
     */
    @Override
    public String toString() {
        if (id == 0) {
            return "X";
        } else if (id == 1){
            return "O";
        }

        return " ";
    }
}
