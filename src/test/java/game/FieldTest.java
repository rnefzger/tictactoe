package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private Field field;
    private Field sameField;

    @BeforeEach
    void init() {
        field = new Field(0);
        sameField = new Field(0);
    }

    @Test
    void printAField() {
        assertEquals("[ ]", field.toString());
    }

    @Test
    void testEquals() {
        assertEquals(field, sameField);
    }

    @Test
    void testHashCode() {
        assertEquals(sameField.hashCode(), field.hashCode());
    }
}