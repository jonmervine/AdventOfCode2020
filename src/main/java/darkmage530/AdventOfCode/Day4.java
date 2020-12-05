package darkmage530.AdventOfCode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import darkmage530.AdventOfCode.util.Utilities;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day4 {
    /*
        byr (Birth Year)
        iyr (Issue Year)
        eyr (Expiration Year)
        hgt (Height)
        hcl (Hair Color)
        ecl (Eye Color)
        pid (Passport ID)
        cid (Country ID)
     */

    public static void main(String[] args) {
        Day4 day4 = new Day4();

        List<String> fromFile = Utilities.getStringFromFile("day4input.txt");
        List<Map<String, String>> passports = day4.parseFile(fromFile);
        int validPassports = day4.processPassports(passports);

        System.out.println("Valid passports: " + validPassports);
    }

    private int processPassports(List<Map<String, String>> passports) {
        int validPassports = 0;
        for (Map<String, String> passport : passports) {
            if (passport.containsKey("byr") && passport.containsKey("iyr")
            && passport.containsKey("eyr") && passport.containsKey("hgt")
            && passport.containsKey("hcl") && passport.containsKey("ecl")
            && passport.containsKey("pid")) {

                if (birthYearValid(passport.get("byr")) && issueYearValid(passport.get("iyr"))
                        && expirationYearValid(passport.get("eyr")) && heightValid(passport.get("hgt"))
                        && hairColorValid(passport.get("hcl")) && eyeColorValid(passport.get("ecl"))
                        && passportIdValid(passport.get("pid")))
                validPassports++;
            }
        }
        return validPassports;
    }

    private boolean birthYearValid(String birthYear) {
//        byr (Birth Year) - four digits; at least 1920 and at most 2002.
        return birthYear.matches("^(19[2-9][0-9]|200[0-2])$");
    }

    private boolean issueYearValid(String issueYear) {
//        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        return issueYear.matches("^(20[1][0-9]|20[2][0])$");
    }

    private boolean expirationYearValid(String expirationYear) {
//        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        return expirationYear.matches("^(20[2][0-9]|2030)$");
    }
    private boolean heightValid(String height) {
//        hgt (Height) - a number followed by either cm or in:
//        If cm, the number must be at least 150 and at most 193.
//        If in, the number must be at least 59 and at most 76.
        return height.matches("^((1[5-8][0-9]|19[0-3])cm|(59|[6][0-9]|7[0-6])in)$");
    }

    private boolean hairColorValid(String hairColor) {
//        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        return hairColor.matches("^#[0-9a-f]{6}$");
    }

    private final Set<String> EYE_COLOR_OPTIONS = Sets.newHashSet("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    private boolean eyeColorValid(String eyeColor) {
//        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        return EYE_COLOR_OPTIONS.contains(eyeColor);
    }

    private boolean passportIdValid(String passportId) {
//        pid (Passport ID) - a nine-digit number, including leading zeroes.
        return passportId.length() == 9 && passportId.matches("^\\d{9}$");
    }

    private List<Map<String, String>> parseFile(List<String> fileText) {
        List<Map<String, String>> passports = Lists.newArrayList();
        Map<String, String> passport = null;
        for (String line : fileText) {
            if (passport == null) {
                passport = Maps.newHashMap();
            }
            if (line.isEmpty()) {
                passports.add(passport);
                passport = null;
                continue;
            }

            String[] lineSplit = line.split("[: ]");

            for (int i = 0; i < lineSplit.length; i = i + 2) {
                passport.put(lineSplit[i].trim(), lineSplit[i + 1].trim());
            }
        }

        return passports;
    }
}
