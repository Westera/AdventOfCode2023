package day09;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day09 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Day09().part1("inputs/Day09"));
        System.out.println(new Day09().part2("inputs/Day09"));
    }

    public int part1(String inputPath) throws IOException {
        return DailyInputReader.getInputFileToLines(inputPath).map(row -> Arrays.stream(row.split(" ")).map(Integer::parseInt).toList()).mapToInt(this::findNextNumber).sum();
    }
    public long part2(String inputPath) throws IOException {
        return DailyInputReader.getInputFileToLines(inputPath).map(row -> Arrays.stream(row.split(" ")).map(Integer::parseInt).toList()).mapToInt(this::findPreviousNumber).sum();
    }

    private int findNextNumber(List<Integer> numbers) {
        if(allSame(numbers)) {
            return numbers.getFirst();
        }
        else {
            List<Integer> newIntegers = new ArrayList<>();
            for(int i = 0 ; i < numbers.size() - 1 ; i++) {
                newIntegers.add(numbers.get(i + 1) - numbers.get(i));
            }
            return numbers.getLast() + findNextNumber(newIntegers);
        }
    }

    private int findPreviousNumber(List<Integer> numbers) {
        if(allSame(numbers)) {
            return numbers.getFirst();
        }
        else {
            List<Integer> newIntegers = new ArrayList<>();
            for(int i = 0 ; i < numbers.size() - 1 ; i++) {
                newIntegers.add(numbers.get(i + 1) - numbers.get(i));
            }
            int value = findPreviousNumber(newIntegers);
            return  numbers.getFirst() - value;
        }
    }

    private boolean allSame(List<Integer> ints) {
        int first = ints.getFirst();
        for (int i = 1; i < ints.size() ; i++)
            if (ints.get(i) != first)
                return false;
        return true;
    }

}
