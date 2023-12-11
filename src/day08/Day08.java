package day08;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.*;

public class Day08 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Day08().part1("inputs/Day08"));
        System.out.println(new Day08().part2("inputs/Day08"));
    }

    public int part1(String inputPath) throws IOException {
        List<String> input = DailyInputReader.getInputFileToLines(inputPath).toList();
        String directions = input.getFirst();
        Map<String, MapPath> map = createMap(input.subList(2, input.size()));
        return traverseMap(map, directions);
    }
    public long part2(String inputPath) throws IOException {
        List<String> input = DailyInputReader.getInputFileToLines(inputPath).toList();
        String directions = input.getFirst();
        GhostMap ghostMap = createGhostMap(input.subList(2, input.size()));
        return traverseGhostMap(ghostMap, directions);
    }

    private Map<String, MapPath> createMap(List<String> rows) {
        Map<String, MapPath> map = new HashMap<>();
        for(String row : rows) {
            String[] data = row.split(" = ");
            String key = data[0];
            String[] leftRight = data[1].substring(1, data[1].length()-1).split(", ");
            map.put(key, new MapPath(leftRight[0], leftRight[1]));
        }
        return map;
    }

    private GhostMap createGhostMap(List<String> rows) {
        Map<String, MapPath> map = new HashMap<>();
        List<String> startingPos = new ArrayList<>();
        for(String row : rows) {
            String[] data = row.split(" = ");
            String key = data[0];
            String[] leftRight = data[1].substring(1, data[1].length()-1).split(", ");
            map.put(key, new MapPath(leftRight[0], leftRight[1]));
            if(key.endsWith("A")) {
                startingPos.add(key);
            }
        }
        return new GhostMap(startingPos, map);
    }

    private int traverseMap(Map<String, MapPath> map, String directions) {
        String to = "ZZZ";
        String currentLocation = "AAA";
        int nrOfSteps= 0;
        int directionIndex = 0;
        while (!Objects.equals(currentLocation, to)) {
            MapPath choice = map.get(currentLocation);
            if(directions.charAt(directionIndex) == 'L') {
                currentLocation = choice.left;
            } else {
                currentLocation = choice.right;
            }
            nrOfSteps++;
            directionIndex = (directionIndex + 1) % directions.length();
        }
        return nrOfSteps;
    }

    private long traverseGhostMap(GhostMap map, String directions) {
        List<String> currentLocations = map.startingPos;
        int directionIndex = 0;
        List<Long> cycles = new ArrayList<>();
        List<Long> tempCycles = new ArrayList<>(Collections.nCopies(currentLocations.size(), 0L));
        do {
            for(int i = 0 ; i < currentLocations.size() ; i++) {
                MapPath choice = map.ghostMap.get(currentLocations.get(i));
                if(directions.charAt(directionIndex) == 'L') {
                    currentLocations.set(i,choice.left);
                } else {
                    currentLocations.set(i, choice.right);
                }
                tempCycles.set(i, tempCycles.get(i) + 1);
                if(currentLocations.get(i).endsWith("Z")) {
                    currentLocations.remove(i);
                    cycles.add(tempCycles.remove(i));
                    i--;
                }
            }
            directionIndex = (directionIndex + 1) % directions.length();
        } while (!currentLocations.isEmpty());
        return cycles.stream().reduce(1L, (x, y) -> (x * y) / findGcd(x, y));
    }

    private long findGcd(long x, long y) {
        if (y == 0) {
            return  x;
        }
        return findGcd(y, x % y);
    }

    public record GhostMap (List<String> startingPos, Map<String, MapPath> ghostMap){}
    public record MapPath (String left, String right){}
}
