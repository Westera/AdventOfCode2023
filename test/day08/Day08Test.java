package day08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day08Test {
    @Test
    public void testPart1Map1() throws IOException {
        int actual = new Day08().part1("inputs/Day08Test");
        Assertions.assertEquals(2, actual);
    }

    @Test
    public void testPart1Map2() throws IOException {
        int actual = new Day08().part1("inputs/Day08Test2");
        Assertions.assertEquals(6, actual);
    }

    @Test
    public void testPart2() throws IOException {
        long actual = new Day08().part2("inputs/Day08Test3");
        Assertions.assertEquals(6, actual);
    }
}
