package com.bsu.lab.util.validation;

import com.bsu.lab.util.constants.ConstantsForHouseCreating;

import static com.bsu.lab.util.SecuredNumbersScanner.EnteringInfoCheck;

public class validationForEntrancesCount {
    private final static int MAX_ENTRANCES_COUNT = 20;
    private final static int MIN_ENTRANCES_COUNT = 1;

    public static int validate() {
        int entrancesCount = EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_ENTRANCES_COUNT);
        if (entrancesCount < MIN_ENTRANCES_COUNT || entrancesCount > MAX_ENTRANCES_COUNT) {
            do {
                System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
                entrancesCount = EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_ENTRANCES_COUNT);
            } while (entrancesCount < MIN_ENTRANCES_COUNT || entrancesCount > MAX_ENTRANCES_COUNT);
        }
        return entrancesCount;
    }
}
