package day10;

import utils.DailyInputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day10 {

    private PipePart pipePart1 = new PipePart('|', List.of(), List.of(), List.of('|', '7', 'F'), List.of('|', 'L', 'J')),
                     pipePart2 = new PipePart('-', List.of('-', 'L', 'F'), List.of('-', 'J', '7'), List.of(), List.of()),
                     pipePart3 = new PipePart('L', List.of(), List.of('-', 'J', '7'), List.of('|', '7', 'F'), List.of()),
                     pipePart4 = new PipePart('J', List.of('-', 'L', 'F'), List.of(), List.of('|', '7', 'F'), List.of()),
                     pipePart5 = new PipePart('7', List.of('-', 'L', 'F'), List.of(), List.of(), List.of('|', 'L', 'J')),
                     pipePart6 = new PipePart('F', List.of(), List.of('-', 'J', '7'), List.of(), List.of('|', 'L', 'J')),
                     pipePart7 = new PipePart('S', List.of('-', 'L', 'F'), List.of('-', 'J', '7'), List.of('|', '7', 'F'), List.of('|', 'L', 'J'));
    private Map<Character, PipePart> pipePartMap = Map.ofEntries(
            Map.entry('|', pipePart1),
            Map.entry('-', pipePart2),
            Map.entry('L', pipePart3),
            Map.entry('J', pipePart4),
            Map.entry('7', pipePart5),
            Map.entry('F', pipePart6),
            Map.entry('S', pipePart7)
    );
    public static void main(String[] args) throws IOException {
        System.out.println(new Day10().part1("inputs/Day10"));
        System.out.println(new Day10().part2("inputs/Day10"));
    }

    public int part1(String inputPath) throws IOException {
        List<String> sewerMap = DailyInputReader.getInputFileToLines(inputPath).toList();
        return findLoopLength(sewerMap);
    }
    public double part2(String inputPath) throws IOException {
        List<String> sewerMap = DailyInputReader.getInputFileToLines(inputPath).toList();
        return findLoopArea(sewerMap);
    }

    private int findLoopLength(List<String> sewerMap) {
        int distance = 0;
        Location start = findAnimalStart(sewerMap);
        List<Location> currentPipes = new ArrayList<>(List.of(start));
        List<Location> visited = new ArrayList<>(List.of(start));
        do {
            currentPipes = findConnections(currentPipes, sewerMap, visited);
            visited.addAll(currentPipes);
            distance++;
        } while(!currentPipes.get(0).equals(currentPipes.get(1)));
        return distance;
    }

    private double findLoopArea(List<String> sewerMap) {
        Location start = findAnimalStart(sewerMap);
        Location currentPipe = start;
        List<Location> visited = new ArrayList<>(List.of(start));
        do {
            currentPipe = findConnection(currentPipe, sewerMap, visited);
            if(currentPipe != null) {
                visited.add(currentPipe);
            }
        } while(currentPipe != null);
        List<Location> corners = new ArrayList<>(visited.stream().filter(location -> location.pipePart.pipeChar == 'L' || location.pipePart.pipeChar == 'J' || location.pipePart.pipeChar == '7' || location.pipePart.pipeChar == 'F').toList());
        //Check if 'S' is a corner
        if(visited.get(1).x != visited.getLast().x() && visited.get(1).y != visited.getLast().y()) {
            corners.add(start);
        }

        double a = 0.0;
        for (int i = 0; i < corners.size() - 1; i++) {
            a += corners.get(i).x * corners.get(i + 1).y - corners.get(i + 1).x * corners.get(i).y;
        }
        a = Math.abs(a + corners.getLast().x * corners.getFirst().y - corners.getFirst().x * corners.getLast().y) / 2.0;
        int b = visited.size();

        return a + 1 - b/2;
    }

    private List<Location> findConnections(List<Location> currentConnections, List<String> sewerMap, List<Location> visited) {
        List<Location> newPipePartLocations = new ArrayList<>();
        for(Location location : currentConnections) {
            PipePart pipePart = pipePartMap.get(sewerMap.get(location.y).charAt(location.x));
            if(location.y - 1 >= 0 && pipePart.connectUp.contains(sewerMap.get(location.y - 1).charAt(location.x))) {
                //We can go up
                Location newLoc = new Location(location.x, location.y - 1, pipePartMap.get(sewerMap.get(location.y - 1).charAt(location.x)));
                if(!visited.contains(newLoc))
                    newPipePartLocations.add(newLoc);
            }
            if (location.y + 1 < sewerMap.size() && pipePart.connectDown.contains(sewerMap.get(location.y + 1).charAt(location.x))) {
                //We can go down
                Location newLoc = new Location(location.x, location.y + 1, pipePartMap.get(sewerMap.get(location.y + 1).charAt(location.x)));
                if(!visited.contains(newLoc))
                    newPipePartLocations.add(newLoc);
            }
            if (location.x - 1 >= 0 && pipePart.connectLeft.contains(sewerMap.get(location.y).charAt(location.x - 1))) {
                //We can go left
                Location newLoc = new Location(location.x - 1, location.y, pipePartMap.get(sewerMap.get(location.y).charAt(location.x - 1)));
                if(!visited.contains(newLoc))
                    newPipePartLocations.add(newLoc);
            }
            if (location.x + 1 < sewerMap.get(location.y).length() && pipePart.connectRight.contains(sewerMap.get(location.y).charAt(location.x + 1))) {
                //We can go right
                Location newLoc = new Location(location.x + 1, location.y, pipePartMap.get(sewerMap.get(location.y).charAt(location.x + 1)));
                if(!visited.contains(newLoc))
                    newPipePartLocations.add(newLoc);
            }
            //dead end
        }
        return newPipePartLocations;
    }

    private Location findConnection(Location location, List<String> sewerMap, List<Location> visited) {
        PipePart pipePart = pipePartMap.get(sewerMap.get(location.y).charAt(location.x));
        if(location.y - 1 >= 0 && pipePart.connectUp.contains(sewerMap.get(location.y - 1).charAt(location.x))) {
            //We can go up
            Location newLoc = new Location(location.x, location.y - 1, pipePartMap.get(sewerMap.get(location.y - 1).charAt(location.x)));
            if(!visited.contains(newLoc))
                return newLoc;
        }
        if (location.y + 1 < sewerMap.size() && pipePart.connectDown.contains(sewerMap.get(location.y + 1).charAt(location.x))) {
            //We can go down
            Location newLoc = new Location(location.x, location.y + 1, pipePartMap.get(sewerMap.get(location.y + 1).charAt(location.x)));
            if(!visited.contains(newLoc))
                return newLoc;
        }
        if (location.x - 1 >= 0 && pipePart.connectLeft.contains(sewerMap.get(location.y).charAt(location.x - 1))) {
            //We can go left
            Location newLoc = new Location(location.x - 1, location.y, pipePartMap.get(sewerMap.get(location.y).charAt(location.x - 1)));
            if(!visited.contains(newLoc))
                return newLoc;
        }
        if (location.x + 1 < sewerMap.get(location.y).length() && pipePart.connectRight.contains(sewerMap.get(location.y).charAt(location.x + 1))) {
            //We can go right
            Location newLoc = new Location(location.x + 1, location.y, pipePartMap.get(sewerMap.get(location.y).charAt(location.x + 1)));
            if(!visited.contains(newLoc))
                return newLoc;
        }
        return null;
    }

    private Location findAnimalStart(List<String> sewerMap) {
        int x = 0, y = 0;
        for(int i = 0 ; i < sewerMap.size() ; i++) {
            y = i;
            x = sewerMap.get(i).indexOf('S');
            if(x != -1) {
                break;
            }
        }
        return new Location(x, y, pipePartMap.get('S'));
    }

    public record Location(int x, int y, PipePart pipePart){
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Location ){
                return x == ((Location) obj).x && y == ((Location) obj).y;
            }
            return false;
        }
    }
    public record PipePart(char pipeChar, List<Character> connectLeft, List<Character> connectRight, List<Character> connectUp, List<Character> connectDown){}
}
