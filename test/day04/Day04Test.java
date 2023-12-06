package day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day04Test {

    @Test
    public void testPart1() throws IOException {
        int actual = new Day04().part1("inputs/Day04Test");
        Assertions.assertEquals(13, actual);
    }

    @Test
    public void testPart2() throws IOException {
        int actual = new Day04().part2("inputs/Day04Test");
        Assertions.assertEquals(30, actual);
    }
}
