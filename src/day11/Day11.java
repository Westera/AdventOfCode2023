package day11;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day11 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Day11().part1("inputs/Day11"));
        System.out.println(new Day11().part2("inputs/Day11", 999999));
    }

    public long part1(String inputPath) throws IOException {
        List<String> galaxyMap = DailyInputReader.getInputFileToLines(inputPath).toList();
        ExpandedUniverse expandedUniverse = findGalaxies(galaxyMap, 1);
        return calculateDistances(expandedUniverse);
    }
    public long part2(String inputPath, int increment) throws IOException {
        List<String> galaxyMap = DailyInputReader.getInputFileToLines(inputPath).toList();
        ExpandedUniverse expandedUniverse = findGalaxies(galaxyMap, increment);
        return calculateDistances(expandedUniverse);
    }

    private ExpandedUniverse findGalaxies(List<String> galaxyMap, int increment) {
        List<String> expandingGalaxyMap = new ArrayList<>(galaxyMap);
        List<Location> galaxies = new ArrayList<>();
        boolean[] foundGalaxyY = new boolean[expandingGalaxyMap.getFirst().length()];
        long[] yDelta = new long[galaxyMap.size()];
        long delta = 0;
        for(int i = 0 ; i < expandingGalaxyMap.size() ; i++) {
            boolean foundGalaxyX = false;
            for(int j = 0 ; j < expandingGalaxyMap.get(i).length() ; j++) {
                if(expandingGalaxyMap.get(i).charAt(j) == '#') {
                    foundGalaxyX = true;
                    foundGalaxyY[j] = true;
                    galaxies.add(new Location(j, i));
                }
            }
            if(!foundGalaxyX) {
                delta += increment;
            }
            yDelta[i] = delta;
        }
        long[] xDelta = new long[foundGalaxyY.length];
        delta = 0;
        for(int i = 0 ; i < foundGalaxyY.length ; i++) {
            if(!foundGalaxyY[i]) {
                delta += increment;
            }
            xDelta[i] = delta;
        }
        return new ExpandedUniverse(galaxies, xDelta, yDelta);
    }

    private long calculateDistances(ExpandedUniverse expandedUniverse) {
        long totalDistance = 0;
        for(int i = 0 ; i < expandedUniverse.galaxies.size() - 1 ; i++) {
            Location galaxy1 = expandedUniverse.galaxies.get(i);
            for(int j = i + 1 ; j < expandedUniverse.galaxies.size() ; j++) {
                Location galaxy2 = expandedUniverse.galaxies.get(j);
                totalDistance += Math.abs(galaxy1.x - galaxy2.x) + Math.abs(galaxy1.y - galaxy2.y) +
                        Math.abs(expandedUniverse.expansionXDeltas[galaxy1.x] - expandedUniverse.expansionXDeltas[galaxy2.x]) +
                        Math.abs(expandedUniverse.expansionYDeltas[galaxy1.y] - expandedUniverse.expansionYDeltas[galaxy2.y]);
            }
        }
        return totalDistance;
    }

    public record ExpandedUniverse(List<Location> galaxies, long[] expansionXDeltas, long[] expansionYDeltas){}
    public record Location(int x, int y) {}
}
