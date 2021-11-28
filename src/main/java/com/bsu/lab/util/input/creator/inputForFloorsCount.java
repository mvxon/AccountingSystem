package com.bsu.lab.util.input.creator;

import com.bsu.lab.util.input.SecuredNumbersScanner;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;

public class inputForFloorsCount {
    private final static int MAX_FLOORS_COUNT = 163;
    private final static int MIN_FLOORS_COUNT = 1;

    public static int input() {
        int floorsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
        if (floorsCount < MIN_FLOORS_COUNT || floorsCount > MAX_FLOORS_COUNT)
            do {
                System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
                floorsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
            } while (floorsCount < MIN_FLOORS_COUNT|| floorsCount > MAX_FLOORS_COUNT);
        return floorsCount;
    }
}
