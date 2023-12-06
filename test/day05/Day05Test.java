package day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day05Test {

    @Test
    public void testPart1() throws IOException {
        long actual = new Day05().part1("inputs/Day05Test");
        Assertions.assertEquals(35, actual);
    }

    @Test
    public void testPart2() throws IOException {
        long actual = new Day05().part2("inputs/Day05Test");
        Assertions.assertEquals(46, actual);
    }
}
