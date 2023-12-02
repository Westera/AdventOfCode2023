package day02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day02Test {

    @Test
    public void testPart1() throws IOException {
        int actual = new Day02().part1("inputs/Day02Test");
        Assertions.assertEquals(8, actual);
    }

    @Test
    public void testPart2() throws IOException {
        int actual = new Day02().part2("inputs/Day02Test");
        Assertions.assertEquals(2286, actual);
    }

    @Test
    public void testRow1() {
        int actual = new Day02().getIndexOfPossibleGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        Assertions.assertEquals(1, actual);
    }

    @Test
    public void testRow2() {
        int actual = new Day02().getIndexOfPossibleGame("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        Assertions.assertEquals(2, actual);
    }

    @Test
    public void testRow3() {
        int actual = new Day02().getIndexOfPossibleGame("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void testRow4() {
        int actual = new Day02().getIndexOfPossibleGame("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void testRow5() {
        int actual = new Day02().getIndexOfPossibleGame("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        Assertions.assertEquals(5, actual);
    }

    @Test
    public void testPowerRow1() {
        int actual = new Day02().getPowerOfGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        Assertions.assertEquals(48, actual);
    }

    @Test
    public void testPowerRow2() {
        int actual = new Day02().getPowerOfGame("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        Assertions.assertEquals(12, actual);
    }

    @Test
    public void testPowerRow3() {
        int actual = new Day02().getPowerOfGame("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        Assertions.assertEquals(1560, actual);
    }

    @Test
    public void testPowerRow4() {
        int actual = new Day02().getPowerOfGame("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        Assertions.assertEquals(630, actual);
    }

    @Test
    public void testPowerRow5() {
        int actual = new Day02().getPowerOfGame("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        Assertions.assertEquals(36, actual);
    }
}
