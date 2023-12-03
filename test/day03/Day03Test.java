package day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day03Test {

    @Test
    public void testPart1() throws IOException {
        int actual = new Day03().part1("inputs/Day03Test");
        Assertions.assertEquals(4361, actual);
    }

    @Test
    public void testPart2() throws IOException {
        int actual = new Day03().part2("inputs/Day03Test");
        Assertions.assertEquals(467835, actual);
    }

    @Test
    public void testPotentialPartsInRow1() {
        List<Day03.Part> actual = Arrays.asList(new Day03.Part(467,
                Arrays.asList(new Day03.Position(0, 0),
                        new Day03.Position(1, 0),
                        new Day03.Position(2, 0))),
                new Day03.Part(114,
                        Arrays.asList(new Day03.Position(5, 0),
                                new Day03.Position(6, 0),
                                new Day03.Position(7, 0))));
        new Day03().getPotentialPartsInRow("467..114..", 0);
    }
}
