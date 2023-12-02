package day02;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Day02 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Day02().part1("inputs/Day02"));
        System.out.println(new Day02().part2("inputs/Day02"));
    }

    public int part1(String inputPath) throws IOException {
        return DailyInputReader.getInputFileToLines(inputPath).mapToInt(this::getIndexOfPossibleGame).sum();
    }

    public int part2(String inputPath) throws IOException {
        return DailyInputReader.getInputFileToLines(inputPath).mapToInt(this::getPowerOfGame).sum();
    }

    int getPowerOfGame(String gameRow) {
        String[] game = gameRow.split(": ");
        int[] minBalls = Arrays.stream(game[1].split("; ")).map(this::requiredBalls).reduce((round1, rounds) ->
            new int[]{Math.max(rounds[0], round1[0]),Math.max(rounds[1], round1[1]), Math.max(rounds[2], round1[2])}).orElseThrow();

        return minBalls[0] * minBalls[1] * minBalls[2];
    }

    int getIndexOfPossibleGame(String gameRow) {
        String[] game = gameRow.split(": ");
        boolean possible = Arrays.stream(game[1].split("; ")).map(this::possibleRound)
            .reduce((round1, round2) -> round1 && round2).orElseThrow();

        if(possible){
            return Integer.parseInt(game[0].substring(5));
        }
        return 0;
    }

    private boolean possibleRound(String round) {
        int redMax = 12, greenMax = 13, blueMax = 14;
        return Arrays.stream(round.split(", ")).map(ball -> {
            String[] result = ball.split(" ");
            int amount = Integer.parseInt(result[0]);
            return switch (result[1]) {
                case "red" -> amount <= redMax;
                case "green" -> amount <= greenMax;
                case "blue" -> amount <= blueMax;
                default -> true;
            };
        }).reduce((ball1, ball2) -> ball1 && ball2).orElseThrow();
    }

    private int[] requiredBalls(String round) {
        AtomicInteger amountRed = new AtomicInteger(), amountGreen = new AtomicInteger(), amountBlue = new AtomicInteger();
        Arrays.stream(round.split(", ")).forEach(ball -> {
            String[] result = ball.split(" ");
            int amount = Integer.parseInt(result[0]);
            switch (result[1]) {
                case "red" -> amountRed.set(amount);
                case "green" -> amountGreen.set(amount);
                case "blue" -> amountBlue.set(amount);
            }
        });
        return new int[] {amountRed.get(), amountGreen.get(), amountBlue.get()};
    }
}
