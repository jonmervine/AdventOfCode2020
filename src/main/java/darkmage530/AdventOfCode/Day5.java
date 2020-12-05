package darkmage530.AdventOfCode;

import com.google.common.collect.Sets;
import darkmage530.AdventOfCode.util.Utilities;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day5 {

    public static void main(String[] args) {
        Day5 day5 = new Day5();

        List<String> planeSeats = Utilities.getStringFromFile("day5input.txt");

        Set<Integer> seatSet = day5.createSet();

//        int maxSeat = day5.processSeats(planeSeats, seatSet);
        Set<Integer> remainingSeatSet = day5.processSeats(planeSeats, seatSet);
        List<Integer> sortedSeats = remainingSeatSet.stream().sorted().collect(Collectors.toList());

        for (int i = 1; i < sortedSeats.size() - 1; i++) {
            if (sortedSeats.get(i) - 1 != sortedSeats.get(i-1) && sortedSeats.get(i) + 1 != sortedSeats.get(i+1)) {
                System.out.println("Could be: " + sortedSeats.get(i));
            }
        }
        System.out.println(sortedSeats.toString());
//        System.out.println("Max Seat: " + maxSeat);
    }

    private Set<Integer> createSet(){
        Set<Integer> seatSet = Sets.newHashSet();
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 8; j++) {
                seatSet.add((i * 8) + j);
            }
        }
        return seatSet;
    }

    private Set<Integer> processSeats(List<String> planeSeats, Set<Integer> seatSet) {
//        int maxSeat = 0;
        for (String seat : planeSeats) {
            int seatId = getRow(seat);
            seatSet.remove(seatId);
//            if (tempSeatValue > maxSeat) {
//                maxSeat = tempSeatValue;
//            }
        }
//        return maxSeat;
        return seatSet;
    }

    private int getRow(String seat) {
        int seatRowStart = 0;
        int seatRowEnd = 128;
        int seatColumnStart = 0;
        int seatColumnEnd = 8;

        for (char binaryMarker : seat.toCharArray()) {
            if (binaryMarker == 'F') {
                seatRowEnd = (seatRowEnd - (seatRowEnd - seatRowStart)  / 2);
            } else if (binaryMarker == 'B') {
                seatRowStart = (seatRowStart + (seatRowEnd - seatRowStart) / 2);
            } else if (binaryMarker == 'R') {
                seatColumnStart = ((seatColumnEnd - seatColumnStart) / 2) + seatColumnStart;
            } else if (binaryMarker == 'L') {
                seatColumnEnd = (seatColumnEnd - (seatColumnEnd - seatColumnStart) / 2);
            }
        }
        int seatId = (seatRowStart * 8) + seatColumnStart;
//        System.out.println("Row: " + seatRowStart + " Column: " + seatColumnStart + " SeatID: " + seatId);
        return seatId;
//        return seatRowStart+"x"+seatColumnStart;
    }
}
