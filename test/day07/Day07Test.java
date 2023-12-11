package day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day07Test {

    @Test
    public void testPart1() throws IOException {
        int actual = new Day07().part1("inputs/Day07Test");
        Assertions.assertEquals(6440, actual);
    }

    @Test
    public void testPart2() throws IOException {
        long actual = new Day07().part2("inputs/Day07Test");
        Assertions.assertEquals(5905, actual);
    }
}
