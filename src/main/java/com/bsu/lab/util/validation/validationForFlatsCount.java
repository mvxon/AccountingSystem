package com.bsu.lab.util.validation;

import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.constants.ConstantsForHouseCreating;

public class validationForFlatsCount {
    private final static int MAX_FLATS_COUNT = 20;
    private final static int MIN_FLATS_COUNT = 1;

    public static int validate() {
        int flatsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
        if (flatsCount < MIN_FLATS_COUNT || flatsCount > MAX_FLATS_COUNT) {
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                flatsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
            } while (flatsCount < MIN_FLATS_COUNT || flatsCount > MAX_FLATS_COUNT);
        }
        return flatsCount;
    }
}
