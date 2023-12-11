package day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day06Test {

    @Test
    public void testPart1() throws IOException {
        int actual = new Day06().part1("inputs/Day06Test");
        Assertions.assertEquals(288, actual);
    }

    @Test
    public void testPart2() throws IOException {
        long actual = new Day06().part2("inputs/Day06Test");
        Assertions.assertEquals(71503, actual);
    }
}
