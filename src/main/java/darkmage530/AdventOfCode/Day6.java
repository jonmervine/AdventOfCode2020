package darkmage530.AdventOfCode;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import darkmage530.AdventOfCode.util.Utilities;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {

    public static void main(String[] args) {
        Day6 day6 = new Day6();

        List<String> inputFromFile = Utilities.getStringFromFile("day6input.txt");
        List<Set<Character>> allAnswers = day6.parseAndCombineAnswers(inputFromFile);
        int count = 0;
        for (Set<Character> groupsAnswers : allAnswers) {
            count = count + groupsAnswers.size();
        }
        System.out.println(count);
    }

    private List<Set<Character>> parseAndCombineAnswers(List<String> inputFromFile) {
        List<Set<Character>> allGroupAnswers = Lists.newArrayList();
        List<Set<Character>> groupAnswers = Lists.newArrayList();
        for (String line : inputFromFile) {

            if (line.isEmpty()) {
                Set<Character> groupsAgreedAnswers = Sets.newHashSet(groupAnswers.get(0));
                for (Set<Character> personAnswers : groupAnswers) {
                    groupsAgreedAnswers.retainAll(personAnswers);
                }

                allGroupAnswers.add(groupsAgreedAnswers);
                groupAnswers = Lists.newArrayList();
                continue;
            }

            groupAnswers.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
        }
        return allGroupAnswers;
    }
}
