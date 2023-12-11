package day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day10Test {

    @Test
    public void testPart1() throws IOException {
        int actual = new Day10().part1("inputs/Day10Test");
        Assertions.assertEquals(4, actual);
    }

    @Test
    public void testPart1Map2() throws IOException {
        int actual = new Day10().part1("inputs/Day10Test2");
        Assertions.assertEquals(8, actual);
    }

    @Test
    public void testPart2() throws IOException {
        double actual = new Day10().part2("inputs/Day10Test3");
        Assertions.assertEquals(4, actual);
    }

    @Test
    public void testPart2Map2() throws IOException {
        double actual = new Day10().part2("inputs/Day10Test4");
        Assertions.assertEquals(8, actual);
    }

    @Test
    public void testPart2Map3() throws IOException {
        double actual = new Day10().part2("inputs/Day10Test5");
        Assertions.assertEquals(10, actual);
    }
}
