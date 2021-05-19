package game;

import javax.annotation.processing.Generated;
import java.util.Objects;

/**
 * Represents a Field of the game
 */
public class Field {

    /**
     * Positioning of the field.
     */
    private final int index;
    /**
     * Indicates whether this field has been conquered.
     */
    private boolean isFilled;
    /**
     * Indicates to whom this field belongs.
     */
    private Player belongsTo;

    Field(final int index) {
        this.index = index;
        isFilled = false;
        belongsTo = new Human(2);
    }

    @Generated({})
    public void setFilled(final boolean filled) {
        isFilled = filled;
    }

    @Generated({})
    public void setBelongsTo(Player belongsTo) {
        this.belongsTo = belongsTo;
    }

    @Generated({})
    public int getIndex() {
        return index;
    }

    @Generated({})
    public Player getBelongsTo() {
        return belongsTo;
    }

    @Generated({})
    public boolean isFilled() {
        return isFilled;
    }

    @Override
    @Generated({})
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return index == field.index;
    }

    @Override
    @Generated({})
    public int hashCode() {
        return Objects.hash(index);
    }

    /**
     * Returns this Field.
     *
     * @return this Field
     */
    @Override
    public String toString() {
        return String.format("[%s]", belongsTo);
    }
}
