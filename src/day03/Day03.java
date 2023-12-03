package day03;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day03 {

    Pattern intPattern = Pattern.compile("(\\d)*");

    public static void main(String[] args) throws IOException {
        System.out.println(new Day03().part1("inputs/Day03"));
        System.out.println(new Day03().part2("inputs/Day03"));
    }

    public int part1(String inputPath) throws IOException {
        List<String> rows = DailyInputReader.getInputFileToLines(inputPath).toList();
        return calcActualPartSum(rows);
    }

    public int part2(String inputPath) throws IOException {
        List<String> rows = DailyInputReader.getInputFileToLines(inputPath).toList();
        return calcGearRatios(rows);
    }

    public List<Part> getPotentialPartsInRow(String row, int rowIndex){
        List<Part> potentialParts = new ArrayList<>();
        Matcher matcher = intPattern.matcher(row);
        while (matcher.find()) {
            if(!matcher.group().isBlank()) {
                potentialParts.add(new Part(Integer.parseInt(matcher.group()), Position.getRange(rowIndex, matcher.start(), matcher.end())));
            }
        }
        return potentialParts;
    }

    private List<Part> getPotentialParts(List<String> schematic) {
        List<Part> potentialParts = new ArrayList<>();
        for(int i = 0 ; i < schematic.size() ; i++) {
            potentialParts.addAll(getPotentialPartsInRow(schematic.get(i), i));
        }
        return potentialParts;
    }
    private int calcActualPartSum(List<String> schematic){
        return getPotentialParts(schematic).stream().filter(part -> isRealPart(part, schematic)).mapToInt(Part::partNumber).sum();
    }

    private boolean isRealPart(Part part, List<String> schematic) {
        return part.positions.stream().anyMatch(pos -> pos.getNeighbours().stream().filter(innerPos -> innerPos.isInBounds(schematic.get(0).length(), schematic.size())).anyMatch(neighbourPos -> isPartIdentifier(schematic.get(neighbourPos.y()).charAt(neighbourPos.x()))));
    }

    private boolean isPartIdentifier(char schematicChar) {
        return schematicChar != '.' && !Character.isDigit(schematicChar);
    }

    public int calcGearRatios(List<String> schematic) {
        int totalRatio = 0;
        List<Part> potentialGearConnectedParts = getPotentialParts(schematic);
        for(int y = 0 ; y < schematic.size() ; y++) {
            String schematicRow = schematic.get(y);
            for(int x = 0 ; x < schematicRow.length() ; x++) {
                if(schematicRow.charAt(x) == '*'){
                    List<Part> partsConnectedToGear = getPartsConnectedToGear(new Position(x, y), potentialGearConnectedParts);
                    if(partsConnectedToGear.size() == 2) {
                        totalRatio += partsConnectedToGear.get(0).partNumber() * partsConnectedToGear.get(1).partNumber();
                    }
                }
            }
        }
        return totalRatio;
    }

    public List<Part> getPartsConnectedToGear(Position position, List<Part> parts) {
        return parts.stream().filter(part -> position.getNeighbours().stream().anyMatch(neighbourPos -> isPositionConnectedToPart(neighbourPos, part))).toList();
    }

    public boolean isPositionConnectedToPart(Position position, Part part){
        return part.positions.contains(position);
    }

    public record Position(int x, int y){
        public List<Position> getNeighbours() {
            return Arrays.asList(new Position(this.x-1, this.y-1),
                    new Position(this.x, this.y-1),
                    new Position(this.x+1, this.y-1),
                    new Position(this.x-1, this.y),
                    new Position(this.x+1, this.y),
                    new Position(this.x-1, this.y+1),
                    new Position(this.x, this.y+1),
                    new Position(this.x+1, this.y+1));
        }

        public static List<Position> getRange(int y, int startX, int endX){
            return IntStream.range(startX, endX).mapToObj(x -> new Position(x, y)).toList();
        }

        public boolean isInBounds(int upperX, int upperY) {
            return x >= 0 && y >= 0 && x < upperX && y < upperY;
        }
    }
    public record Part(int partNumber, List<Position> positions){}
}
