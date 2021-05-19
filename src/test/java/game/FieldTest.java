package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void printAField() {
        Field field = new Field(0);
        assertEquals("[ ]", field.toString());
    }
}