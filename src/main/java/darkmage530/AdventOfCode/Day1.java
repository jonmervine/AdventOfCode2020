package darkmage530.AdventOfCode;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import darkmage530.AdventOfCode.util.Utilities;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        Day1 day1 = new Day1();
//        Pair<Integer, Integer> valuesForTarget = day1.findTargetEntries(Stream.of(5, 10, 100, 5500, 3000, 2010).collect(Collectors.toList()), 2020);
//        valuesForTarget.getKey();

        Pair<Integer, Integer> valuesForTarget = day1.findTargetEntries(Utilities.getIntsFromFile("day1input.txt"), 2020);
        System.out.println("Answer: " + valuesForTarget.getKey() + ", " + valuesForTarget.getValue());
        int product = valuesForTarget.getKey() * valuesForTarget.getValue();
        System.out.println("Product: " + product);

        int threeProduct = day1.findTargetEntriesWithThree(Utilities.getIntsFromFile("day1input.txt"), 2020);
        System.out.println("ThreeProduct: " + threeProduct);
    }

    public Pair<Integer, Integer> findTargetEntries(List<Integer> expenses, int targetNumber) {
        if (expenses == null || expenses.isEmpty()) {
            return null;
        }
        Collections.sort(expenses);

        int xIndex = 0;
        int xValue = expenses.get(xIndex);

        int yIndex = expenses.size() - 1;
        int yValue = expenses.get(yIndex);

        for (int i = 0; i < expenses.size(); i++) {
            int sumValue = xValue + yValue;
            if (sumValue == targetNumber) {
                return new Pair<>(xValue, yValue);
            } else if (sumValue > targetNumber) {
                yValue = expenses.get(--yIndex);
            } else {
                xValue = expenses.get(++xIndex);
            }
        }

        return null;
    }

    public int findTargetEntriesWithThree(List<Integer> expenses, int targetNumber) {
        if (expenses == null || expenses.isEmpty()) {
            return 0;
        }
        Collections.sort(expenses);

        Multimap<Integer, Pair<Integer, Integer>> allPairedSums = ArrayListMultimap.create();
        for (int i = 0; i < expenses.size(); i++) {
            for (int j = 0; j < expenses.size(); j++) {
                if (j != i) {
                    allPairedSums.put(expenses.get(i) + expenses.get(j), new Pair<>(expenses.get(i), expenses.get(j)));
                }
            }
        }

        List<Integer> sortedPairs = new ArrayList<>(allPairedSums.keySet());
        Collections.sort(sortedPairs);

        int xIndex = 0;
        int xValue = expenses.get(xIndex);

        int yIndex = sortedPairs.size() - 1;
        int yValue = sortedPairs.get(yIndex);

        for (int i = 0; i < allPairedSums.size(); i++) {
            int sumValue = xValue + yValue;

            if (sumValue == targetNumber) {
                Collection<Pair<Integer, Integer>> pairs = allPairedSums.get(yValue);
                Pair<Integer, Integer> integerIntegerPair = pairs.stream().findFirst().get();
                System.out.println(integerIntegerPair.getKey() + " " + integerIntegerPair.getValue() + " " + xValue);
                return integerIntegerPair.getValue() * integerIntegerPair.getKey() * xValue;
            } else if (sumValue > targetNumber) {
                yValue = sortedPairs.get(--yIndex);
            } else {
                xValue = expenses.get(++xIndex);
            }
        }

        return 0;
    }
}
