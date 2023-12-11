package day07;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 {

    private final Map<Character, Integer> CARD_VALUES = Map.ofEntries(
            Map.entry('2', 2),
            Map.entry('3', 3),
            Map.entry('4', 4),
            Map.entry('5', 5),
            Map.entry('6', 6),
            Map.entry('7', 7),
            Map.entry('8', 8),
            Map.entry('9', 9),
            Map.entry('T', 10),
            Map.entry('J', 11),
            Map.entry('Q', 12),
            Map.entry('K', 13),
            Map.entry('A', 14));

    private final Map<Character, Integer> JOKER_VALUES = Map.ofEntries(
            Map.entry('2', 2),
            Map.entry('3', 3),
            Map.entry('4', 4),
            Map.entry('5', 5),
            Map.entry('6', 6),
            Map.entry('7', 7),
            Map.entry('8', 8),
            Map.entry('9', 9),
            Map.entry('T', 10),
            Map.entry('J', 1),
            Map.entry('Q', 12),
            Map.entry('K', 13),
            Map.entry('A', 14));

    public static void main(String[] args) throws IOException {
        System.out.println(new Day07().part1("inputs/Day07"));
        System.out.println(new Day07().part2("inputs/Day07"));
    }

    public int part1(String inputPath) throws IOException {
        List<Player> resultOfMatch = DailyInputReader.getInputFileToLines(inputPath).map(this::createPlayer).sorted((player1, player2) -> {
            if(player1.handRank == player2.handRank) {
                return compareHands(player1.hand, player2.hand);
            }
            else
                return Integer.compare(player1.handRank, player2.handRank);
        }).toList();

        int totalWinnings = 0;
        for (int i = 0 ; i < resultOfMatch.size() ; i++) {
            totalWinnings += resultOfMatch.get(i).bet * (i + 1);
        }

        return totalWinnings;
    }

    public int part2(String inputPath) throws IOException {
        List<Player> resultOfMatch = DailyInputReader.getInputFileToLines(inputPath).map(this::createJokerPlayer).sorted((player1, player2) -> {
            if(player1.handRank == player2.handRank) {
                return compareJokerHands(player1.hand, player2.hand);
            }
            else
                return Integer.compare(player1.handRank, player2.handRank);
        }).toList();

        int totalWinnings = 0;
        for (int i = 0 ; i < resultOfMatch.size() ; i++) {
            totalWinnings += resultOfMatch.get(i).bet * (i + 1);
        }

        return totalWinnings;
    }

    private Player createPlayer(String dataRow) {
        String[] data = dataRow.split(" ");
        int handRank = getHandRank(createOccurranceMap(data[0]));
        return new Player(data[0], Integer.parseInt(data[1]), handRank);
    }

    private Player createJokerPlayer(String dataRow) {
        String[] data = dataRow.split(" ");
        int handRank = getHandRank(createJokerOccurranceMap(data[0]));
        return new Player(data[0], Integer.parseInt(data[1]), handRank);
    }

    private Map<Character, Integer> createOccurranceMap(String hand) {
        Map<Character, Integer> occuranceMap = new HashMap<>();
        for (char c : hand.toCharArray()) {
            occuranceMap.put(c, occuranceMap.getOrDefault(c, 0) + 1);
        }
        return occuranceMap;
    }

    private Map<Character, Integer> createJokerOccurranceMap(String hand) {
        Map<Character, Integer> occuranceMap = new HashMap<>();
        int nrOfJokers = 0;
        for (char c : hand.toCharArray()) {
            if(c != 'J') {
                occuranceMap.put(c, occuranceMap.getOrDefault(c, 0) + 1);
            } else {
                nrOfJokers++;
            }
        }
        for (char c : occuranceMap.keySet()) {
            occuranceMap.put(c, occuranceMap.get(c) + nrOfJokers);
        }
        return occuranceMap;
    }

    private int getHandRank(Map<Character, Integer> occuranceMap) {
        if(occuranceMap.isEmpty() || occuranceMap.size() == 1) {
            return 7;
        } else if (occuranceMap.size() == 2) {
            if (occuranceMap.containsValue(4)) {
                return 6;
            } else {
                return 5;
            }
        } else if (occuranceMap.size() == 3) {
            if(occuranceMap.containsValue(3)){
                return 4;
            } else {
                return 3;
            }
        } else if (occuranceMap.size() == 4) {
            return 2;
        } else {
            return 1;
        }
    }

    private int compareHands(String hand1, String hand2) {
        for(int i = 0 ; i < hand1.length() ; i++) {
            if (hand1.charAt(i) != hand2.charAt(i)) {
                return Integer.compare(CARD_VALUES.get(hand1.charAt(i)), CARD_VALUES.get(hand2.charAt(i)));
            }
        }
        return 0;
    }

    private int compareJokerHands(String hand1, String hand2) {
        for(int i = 0 ; i < hand1.length() ; i++) {
            if (hand1.charAt(i) != hand2.charAt(i)) {
                return Integer.compare(JOKER_VALUES.get(hand1.charAt(i)), JOKER_VALUES.get(hand2.charAt(i)));
            }
        }
        return 0;
    }

    public record Player(String hand, int bet, int handRank){}
}
