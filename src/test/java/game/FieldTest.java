package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void setFilled() {
        Field field = new Field(0);

        field.setFilled(true);

        assertTrue(field.isFilled());
    }
}