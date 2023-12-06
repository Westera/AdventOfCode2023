package day04;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day04 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Day04().part1("inputs/Day04"));
        System.out.println(new Day04().part2("inputs/Day04"));
    }

    public int part1(String inputPath) throws IOException {
        return DailyInputReader.getInputFileToLines(inputPath).mapToInt(this::calcPoints).sum();
    }

    public int part2(String inputPath) throws IOException {
        List<String> rows = DailyInputReader.getInputFileToLines(inputPath).toList();
        return scratchTicketsWithCopies(rows);
    }

    public int calcPoints(String cardRow) {
        AtomicInteger point = new AtomicInteger();
        String[] winsAndNumbers = cardRow.substring(cardRow.indexOf(":") + 2).split("\\s+\\|\\s+");
        List<Integer> wins = Arrays.stream(winsAndNumbers[0].trim().split("\\s+")).map(Integer::parseInt).toList();
        Arrays.stream(winsAndNumbers[1].trim().split("\\s+")).map(Integer::parseInt).forEach(number -> {
            if(isWinningNumber(wins, number)) {
                point.getAndUpdate(pointCalc -> pointCalc == 0 ? 1 : pointCalc*2);
            }
        });
        return point.get();
    }

    public int scratchTicketsWithCopies(List<String> tickets) {
        int[] numberOfTickets = new int[tickets.size()];
        Arrays.fill(numberOfTickets, 1);
        for(int i = 0 ; i < tickets.size() ; i++) {
            AtomicInteger copies = new AtomicInteger(0);
            String cardRow = tickets.get(i);
            String[] winsAndNumbers = cardRow.substring(cardRow.indexOf(":") + 2).split("\\s+\\|\\s+");
            List<Integer> wins = Arrays.stream(winsAndNumbers[0].trim().split("\\s+")).map(Integer::parseInt).toList();
            Arrays.stream(winsAndNumbers[1].trim().split("\\s+")).map(Integer::parseInt).forEach(number -> {
                if(isWinningNumber(wins, number)) {
                    copies.getAndIncrement();
                }
            });
            for(int j = i + 1 ; j < i + 1 + copies.get() ; j++) {
                numberOfTickets[j] += numberOfTickets[i];
            }
        }
        return Arrays.stream(numberOfTickets).sum();
    }

    public boolean isWinningNumber(List<Integer> winningNumbers, int number){
        return winningNumbers.contains(number);
    }
}
