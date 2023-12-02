package day01;

import utils.DailyInputReader;

import java.io.IOException;

public class Day01 {

    enum TextDigits {
        ONE("o1e"),
        TWO("t2o"),
        THREE("t3e"),
        FOUR("4"),
        FIVE("5e"),
        SIX("6"),
        SEVEN("7n"),
        EIGHT("e8t"),
        NINE("n9e");

        final String value;
        TextDigits(String value) {
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println(new Day01().part1("inputs/Day01"));
        System.out.println(new Day01().part2("inputs/Day01"));
    }

    public int part1(String inputPath) throws IOException {
        return DailyInputReader.getInputFileToLines(inputPath).mapToInt(this::readRow).sum();
    }
    public int part2(String inputPath) throws IOException {
        return DailyInputReader.getInputFileToLines(inputPath).map(this::replaceTextDigitsWithIntDigits).mapToInt(this::readRow).sum();
    }

    public int readRow(String row) {
        String reverseRow = new StringBuilder(row).reverse().toString();
        int firstDigit = getFirstDigit(row);
        int secondDigit = getFirstDigit(reverseRow);

        return firstDigit * 10 + secondDigit;
    }

    private int getFirstDigit(String input) {
        return Character.getNumericValue(input.chars().filter(Character::isDigit).findFirst().orElseThrow());
    }

    String replaceTextDigitsWithIntDigits(String input) {
        String manip = input;
        for(TextDigits textDigits : TextDigits.values()){
            manip = manip.replaceAll(textDigits.toString().toLowerCase(), textDigits.value);
        }
        return manip;
    }
}
