package darkmage530.AdventOfCode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

    public static List<Integer> readFile(String fileName) {
        List<Integer> listOfNumbers = new ArrayList<>();
        try (InputStream is = Utilities.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                String line = reader.readLine();
                listOfNumbers.add(Integer.valueOf(line));
            }
        } catch (IOException e) {
            System.out.println("meeep");
        }
        return listOfNumbers;
    }
}
