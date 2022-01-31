package com.bsu.lab.util.input.service;

import com.bsu.lab.util.input.SecuredNumbersScanner;
import com.bsu.lab.constant.ConstantsForHouseCreating;

public class InputForFloorsCount {
    private final static int MAX_FLOORS_COUNT = 163;
    private final static int MIN_FLOORS_COUNT = 1;

    public static int input() {
        int floorsCount = SecuredNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
        while (floorsCount < MIN_FLOORS_COUNT || floorsCount > MAX_FLOORS_COUNT) {
            System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
            floorsCount = SecuredNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
        }
        return floorsCount;
    }
}
