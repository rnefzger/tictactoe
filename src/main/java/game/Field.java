package game;

import javax.annotation.processing.Generated;

public class Field {

    private boolean isFilled;
    private final int index;

    Field(final int index) {
        this.index = index;
        isFilled = false;
    }

    public void setFilled(final boolean filled) {
        isFilled = filled;
    }

    @Generated({})
    public int getIndex() {
        return index;
    }

    @Generated({})
    public boolean isFilled() {
        return isFilled;
    }
}
