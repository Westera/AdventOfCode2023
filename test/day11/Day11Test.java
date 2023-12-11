package day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day11Test {

    @Test
    public void testPart1() throws IOException {
        long actual = new Day11().part1("inputs/Day11Test");
        Assertions.assertEquals(374, actual);
    }

    @Test
    public void testPart2() throws IOException {
        long actual = new Day11().part2("inputs/Day11Test", 9);
        Assertions.assertEquals(1030, actual);
    }

    @Test
    public void testPart2Map2() throws IOException {
        long actual = new Day11().part2("inputs/Day11Test", 99);
        Assertions.assertEquals(8410, actual);
    }
}
