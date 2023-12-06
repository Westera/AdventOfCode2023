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
        long[] seeds = Arrays.stream(input.get(0).substring(7).split(" ")).mapToLong(Long::parseLong).toArray();

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
        long[] seedsRange = Arrays.stream(input.get(0).substring(7).split(" ")).mapToLong(Long::parseLong).toArray();
        List<Long> seeds = new ArrayList<>();
        for(int i = 0 ; i < seedsRange.length - 1 ; i += 2) {
            for(long j = seedsRange[i] ; j < seedsRange[i] + seedsRange[i + 1] ; j++) {
                seeds.add(j);
            }
        }

        for(int i = 2 ; i < input.size() ; i++){
            List<Long> tempSeeds = new ArrayList<>(seeds);
            while(i < input.size() - 1 && !Objects.equals(input.get(++i), "")) {
                long[] mappingNumbers = Arrays.stream(input.get(i).split(" ")).mapToLong(Long::parseLong).toArray();
                for(int seedIndex = 0 ; seedIndex < seeds.size() ; seedIndex++){
                    long mappingNumberDiff = seeds.get(seedIndex) - mappingNumbers[1];
                    if (mappingNumberDiff < mappingNumbers[2] && mappingNumberDiff >= 0) {
                        tempSeeds.set(seedIndex, mappingNumbers[0] + mappingNumberDiff);
                    }
                }
            }
            seeds = new ArrayList<>(tempSeeds);
        }
        Collections.sort(seeds);
        return seeds.get(0);
    }
}
