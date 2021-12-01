package com.bsu.lab.util.input.creator;

import com.bsu.lab.util.constants.ConstantsForHouseCreating;

import static com.bsu.lab.util.input.SecuredNumbersScanner.EnteringInfoCheck;

public class InputForEntrancesCount {
    private final static int MAX_ENTRANCES_COUNT = 20;
    private final static int MIN_ENTRANCES_COUNT = 1;

    public static int input() {
        int entrancesCount = EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_ENTRANCES_COUNT);
        while (entrancesCount < MIN_ENTRANCES_COUNT || entrancesCount > MAX_ENTRANCES_COUNT) {
            System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
            entrancesCount = EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_ENTRANCES_COUNT);
        }

        return entrancesCount;
    }
}
