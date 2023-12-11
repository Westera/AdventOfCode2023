package day05;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.*;

public class Day05 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Day05().part1("inputs/Day05"));
        System.out.println(new Day05().part2("inputs/Day05"));
    }

    public long part1(String inputPath) throws IOException {
        List<String> input = DailyInputReader.getInputFileToLines(inputPath).toList();
        return getLowestLocationForSeeds(input);
    }

    public long part2(String inputPath) throws IOException {
        List<String> input = DailyInputReader.getInputFileToLines(inputPath).toList();
        return getLowestLocationForSeedRange(input);
    }

    public long getLowestLocationForSeeds(List<String> input) {
        long[] seeds = Arrays.stream(input.getFirst().substring(7).split(" ")).mapToLong(Long::parseLong).toArray();

        for(int i = 2 ; i < input.size() ; i++){
            long[] tempSeeds = Arrays.copyOf(seeds, seeds.length);
            while(i < input.size() - 1 && !Objects.equals(input.get(++i), "")) {
                long[] mappingNumbers = Arrays.stream(input.get(i).split(" ")).mapToLong(Long::parseLong).toArray();
                for(int seedIndex = 0 ; seedIndex < seeds.length ; seedIndex++){
                    long mappingNumberDiff = seeds[seedIndex] - mappingNumbers[1];
                    if (mappingNumberDiff < mappingNumbers[2] && mappingNumberDiff >= 0) {
                        tempSeeds[seedIndex] = mappingNumbers[0] + mappingNumberDiff;
                    }
                }
            }
            seeds = Arrays.copyOf(tempSeeds, seeds.length);
        }
        Arrays.sort(seeds);
        return seeds[0];
    }

    public long getLowestLocationForSeedRange(List<String> input) {
        long[] seeds = Arrays.stream(input.getFirst().substring(7).split(" ")).mapToLong(Long::parseLong).toArray();
        List<SeedRange> seedRanges = new ArrayList<>();
        for(int i = 0 ; i < seeds.length ; i += 2) {
            seedRanges.add(new SeedRange(seeds[i], seeds[i] + seeds[i + 1] - 1));
        }

        List<List<Mapping>> allMappings = new ArrayList<>();
        for(int i = 2 ; i < input.size() ; i++){
            List<Mapping> singleMappings = new ArrayList<>();
            while(i < input.size() - 1 && !Objects.equals(input.get(++i), "")) {
                long[] mappingNumbers = Arrays.stream(input.get(i).split(" ")).mapToLong(Long::parseLong).toArray();
                singleMappings.add(new Mapping(mappingNumbers[1], mappingNumbers[0], mappingNumbers[2]));
            }
            allMappings.add(singleMappings);
        }
        return mapOneLevel(seedRanges, allMappings);
    }

    public long mapOneLevel(List<SeedRange> seedRanges, List<List<Mapping>> mappings) {
        if(!mappings.isEmpty()) {
            List<Mapping> currentMapping = mappings.getFirst();
            List<List<Mapping>> nextLevel = mappings.subList(1, mappings.size());
            long minOfRanges = Long.MAX_VALUE;
            for (SeedRange currentSeedRange : seedRanges) {
                long mapped = Long.MAX_VALUE;
                boolean foundMap = false;
                for (Mapping map : currentMapping) {
                    long fromStop = map.from + map.range - 1;
                    long startWhenOutside = map.to + currentSeedRange.start - map.from;
                    long stopWhenOutside = map.to + currentSeedRange.stop - map.from;
                    if (matchesFullRange(map, currentSeedRange)) {
                        foundMap = true;
                        mapped = mapOneLevel(List.of(new SeedRange(startWhenOutside, stopWhenOutside)), nextLevel);
                    } else if (matchesInnerRange(map, currentSeedRange)) {
                        foundMap = true;
                        mapped = Math.min(mapOneLevel(List.of(new SeedRange(map.to, map.to + map.range)), nextLevel),
                                mapOneLevel(List.of(new SeedRange(currentSeedRange.start, map.from - 1), new SeedRange(fromStop + 1, currentSeedRange.stop)), mappings));
                    } else if (matchesLeftRange(map, currentSeedRange)) {
                        foundMap = true;
                        mapped = Math.min(mapOneLevel(List.of(new SeedRange(startWhenOutside, map.to + map.range)), nextLevel),
                                mapOneLevel(List.of(new SeedRange(fromStop + 1, currentSeedRange.stop)), mappings));
                    } else if (matchesRightRange(map, currentSeedRange)) {
                        foundMap = true;
                        mapped = Math.min(mapOneLevel(List.of(new SeedRange(map.to, stopWhenOutside)), nextLevel),
                                mapOneLevel(List.of(new SeedRange(currentSeedRange.start, map.from - 1)), mappings));
                    }
                }
                if (!foundMap) {
                    mapped = mapOneLevel(List.of(currentSeedRange), nextLevel);
                }
                minOfRanges = Math.min(mapped, minOfRanges);
            }
            return minOfRanges;
        }
        return seedRanges.stream().mapToLong(seedRange -> seedRange.start).min().orElseThrow();
    }

    private boolean matchesFullRange(Mapping mapping, SeedRange seed) {
        return mapping.from <= seed.start && seed.stop < mapping.from + mapping.range;
    }

    private boolean matchesInnerRange(Mapping mapping, SeedRange seed) {
        return seed.start < mapping.from && mapping.from + mapping.range <= seed.stop;
    }

    private boolean matchesLeftRange(Mapping mapping, SeedRange seed) {
        return mapping.from <= seed.start && seed.start <= mapping.from + mapping.range - 1;
    }

    private boolean matchesRightRange(Mapping mapping, SeedRange seed) {
        return mapping.from <= seed.stop && seed.stop <= mapping.from + mapping.range - 1;
    }

    public record Mapping (long from, long to, long range){}
    public record SeedRange (long start, long stop){}

}
