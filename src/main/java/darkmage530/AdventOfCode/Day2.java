package darkmage530.AdventOfCode;

import darkmage530.AdventOfCode.util.Utilities;

import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        List<String> stringFromFile = Utilities.getStringFromFile("day2input.txt");

        int correctPasswords = day2.countCorrectPasswords(stringFromFile);
        System.out.println("Correct Passwords: " + correctPasswords);

        int correctPasswords2 = day2.countCorrectPasswords2(stringFromFile);
        System.out.println("Correct Passwords: " + correctPasswords2);
    }

    private int countCorrectPasswords(List<String> passwords) {
        int valid = 0;
        for (String line : passwords) {
            boolean isValid = isValid(line);
            if (isValid) {
                valid++;
            }
        }
        return valid;
    }

    private boolean isValid(String line) {
        String[] splitLine = line.trim().split(" ");
        String[] range = splitLine[0].trim().split("-");
        int min = Integer.parseInt(range[0]);
        int max = Integer.parseInt(range[1]);

        String characterToCount = splitLine[1].replace(":", "").trim();

        String password = splitLine[2].trim();

        int characterCount = password.replaceAll(characterToCount, "@").replaceAll("[a-zA-Z0-9]", "").length();

        if (characterCount < min) {
            return false;
        }
        if (characterCount > max) {
            return false;
        }
         return true;
    }

    private int countCorrectPasswords2(List<String> passwords) {
        int valid = 0;
        for (String line : passwords) {
            boolean isValid = isValid2(line);
            if (isValid) {
                valid++;
            }
        }
        return valid;
    }

    private boolean isValid2(String line) {
        String[] splitLine = line.trim().split(" ");
        String[] range = splitLine[0].trim().split("-");
        int indexShouldHave = Integer.parseInt(range[0]) - 1;
        int indexShouldNotHave = Integer.parseInt(range[1]) - 1;

        char characterToCount = splitLine[1].replace(":", "").trim().charAt(0);

        String password = splitLine[2].trim();

        if (password.charAt(indexShouldHave) == characterToCount && password.charAt(indexShouldNotHave) != characterToCount) {
            return true;
        }
        if (password.charAt(indexShouldHave) != characterToCount && password.charAt(indexShouldNotHave) == characterToCount) {
            return true;
        }
        return false;
    }
}
