package darkmage530.AdventOfCode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Utilities {

    public static List<Integer> getIntsFromFile(String fileName) {
        List<Integer> listOfNumbers = new ArrayList<>();
        Function<String, Integer> intConversion = Integer::valueOf;
        return readFile(fileName, listOfNumbers, intConversion);
    }

    public static List<String> getStringFromFile(String fileName) {
        List<String> listOfStrings = new ArrayList<>();
        return readFile(fileName, listOfStrings, (line) -> line);
    }

    private static <T> List<T> readFile(String fileName, List<T> linesToPopulate, Function<String, T> processingFunction) {
        try (InputStream is = Utilities.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                String line = reader.readLine();
                linesToPopulate.add(processingFunction.apply(line));
            }
        } catch (IOException e) {
            System.out.println("meeep");
        }
        return linesToPopulate;
    }
}
