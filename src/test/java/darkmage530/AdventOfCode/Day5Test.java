package darkmage530.AdventOfCode;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day5Test {

    @Test
    public void testLogic() {
        int seatColumnStart = 0;
        int seatColumnEnd = 8;
        int seatRowStart = 0; // 31 16
        int seatRowEnd = 127; // 63

        for (char binaryMarker : "FBFBBFFRLR".toCharArray()) {
            if (binaryMarker == 'F') {
                seatRowEnd = (seatRowEnd - (seatRowEnd - seatRowStart)  / 2);
            } else if (binaryMarker == 'B') {
                seatRowStart = (seatRowStart + (seatRowEnd - seatRowStart) / 2);
            } else if (binaryMarker == 'R') {
                seatColumnStart = ((seatColumnEnd - seatColumnStart) / 2) + seatColumnStart;
            } else if (binaryMarker == 'L') {
                seatColumnEnd = (seatColumnEnd - (seatColumnEnd - seatColumnStart) / 2);
            }
            System.out.println(seatColumnEnd + " " + seatColumnStart);
            System.out.println(seatRowEnd + " " + seatRowStart);
        }
    }
//    Start by considering the whole range, columns 0 through 7.
//    R means to take the upper half, keeping columns 4 through 7.
//    L means to take the lower half, keeping columns 4 through 5.
//    The final R keeps the upper of the two, column 5.

//    Start by considering the whole range, rows 0 through 127.
//    F means to take the lower half, keeping rows 0 through 63.
//    B means to take the upper half, keeping rows 32 through 63.
//    F means to take the lower half, keeping rows 32 through 47.
//    B means to take the upper half, keeping rows 40 through 47.
//    B keeps rows 44 through 47.
//    F keeps rows 44 through 45.
//    The final F keeps the lower of the two, row 44.
}