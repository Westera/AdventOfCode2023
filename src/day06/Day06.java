package day06;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day06 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Day06().part1("inputs/Day06"));
        System.out.println(new Day06().part2("inputs/Day06"));
    }

    public int part1(String inputPath) throws IOException {
        List<String> input = DailyInputReader.getInputFileToLines(inputPath).toList();
        return boatPressOptimization(input);
    }

    public long part2(String inputPath) throws IOException {
        List<String> input = DailyInputReader.getInputFileToLines(inputPath).toList();
        return boatPressOptimizationSingleRace(input);
    }

    public int boatPressOptimization(List<String> input) {
        int[] raceLengths = Arrays.stream(input.get(0).substring(5).trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] timesToBeat = Arrays.stream(input.get(1).substring(9).trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int total = 1;
        for(int i = 0 ; i < raceLengths.length ; i++) {
            int finalI = i;
            total *= (int) IntStream.range(0, raceLengths[i] + 1).map(x -> x*(raceLengths[finalI] - x)).filter(y -> y > timesToBeat[finalI]).count();
        }
        return total;
    }

    public long boatPressOptimizationSingleRace(List<String> input) {
        long raceLength = Long.parseLong(input.get(0).substring(5).trim().replaceAll("\\s+", ""));
        long timeToBeat = Long.parseLong(input.get(1).substring(9).trim().replaceAll("\\s+", ""));

        double sqrt = Math.sqrt(Math.pow((double) -raceLength / 2, 2) - timeToBeat);
        double x1 = (double) raceLength /2 + sqrt;
        double x2 = (double) raceLength /2 - sqrt;
        x2= Math.ceil(x2);
        x1= Math.ceil(x1);

        return (long) (x1-x2);
    }
}
