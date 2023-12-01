package day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day01Test {
    @Test
    public void testPart1() throws IOException {
        int actual = new Day01().part1("inputs/Day01Test");
        Assertions.assertEquals(142, actual);
    }

    @Test
    public void testPart2() throws IOException {
        int actual = new Day01().part2("inputs/Day01Test2");
        Assertions.assertEquals(281, actual);
    }

    @Test
    public void testReadRow1() {
        int actual = new Day01().readRow("1abc2");
        Assertions.assertEquals(12, actual);
    }

    @Test
    public void testReadRow2() {
        int actual = new Day01().readRow("pqr3stu8vwx");
        Assertions.assertEquals(38, actual);
    }

    @Test
    public void testReadRow3() {
        int actual = new Day01().readRow("a1b2c3d4e5f");
        Assertions.assertEquals(15, actual);
    }

    @Test
    public void testReadRow4() {
        int actual = new Day01().readRow("treb7uchet");
        Assertions.assertEquals(77, actual);
    }

    @Test
    public void testReadRowManip1() {
        String manip = new Day01().replaceTextDigitsWithIntDigits("two1nine");
        int actual = new Day01().readRow(manip);
        Assertions.assertEquals(29, actual);
    }

    @Test
    public void testReadRowManip2() {
        String manip = new Day01().replaceTextDigitsWithIntDigits("eightwothree");
        int actual = new Day01().readRow(manip);
        Assertions.assertEquals(83, actual);
    }

    @Test
    public void testReadRowManip3() {
        String manip = new Day01().replaceTextDigitsWithIntDigits("abcone2threexyz");
        int actual = new Day01().readRow(manip);
        Assertions.assertEquals(13, actual);
    }

    @Test
    public void testReadRowManip4() {
        String manip = new Day01().replaceTextDigitsWithIntDigits("xtwone3four");
        int actual = new Day01().readRow(manip);
        Assertions.assertEquals(24, actual);
    }

    @Test
    public void testReadRowManip5() {
        String manip = new Day01().replaceTextDigitsWithIntDigits("4nineeightseven2");
        int actual = new Day01().readRow(manip);
        Assertions.assertEquals(42, actual);
    }

    @Test
    public void testReadRowManip6() {
        String manip = new Day01().replaceTextDigitsWithIntDigits("zoneight234");
        int actual = new Day01().readRow(manip);
        Assertions.assertEquals(14, actual);
    }

    @Test
    public void testReadRowManip7() {
        String manip = new Day01().replaceTextDigitsWithIntDigits("7pqrstsixteen");
        int actual = new Day01().readRow(manip);
        Assertions.assertEquals(76, actual);
    }
}
