package game;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @Test
    void turn() {
        Player player = new Computer(1);
        List<Integer> values = Stream.iterate(0, x -> ++x).limit(9).collect(Collectors.toList());

        assertTrue(values.contains(player.turn()));
    }
}