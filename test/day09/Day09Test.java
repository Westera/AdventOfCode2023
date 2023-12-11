package day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day09Test {

    @Test
    public void testPart1() throws IOException {
        int actual = new Day09().part1("inputs/Day09Test");
        Assertions.assertEquals(114, actual);
    }

    @Test
    public void testPart2() throws IOException {
        long actual = new Day09().part2("inputs/Day09Test");
        Assertions.assertEquals(2, actual);
    }
}
