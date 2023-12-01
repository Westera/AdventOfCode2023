package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class DailyInputReader {

    public static Stream<String> getInputLineByLine(String input) {
        return input.lines();
    }

    public static String getInputAsString(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    public static Stream<String> getInputFileToLines(String path) throws IOException {
        return Files.lines(Paths.get(path));
    }

    public static Stream<Character> getInputCharByChar(String input) {
        return input.chars().mapToObj(c -> (char) c);
    }

    public static Stream<String> getInputSplitOnX(String input, String x) {
        return Arrays.stream(input.split(x));
    }
}
