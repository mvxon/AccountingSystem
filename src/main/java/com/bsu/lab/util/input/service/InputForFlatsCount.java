package com.bsu.lab.util.input.service;

import com.bsu.lab.util.input.SecuredNumbersScanner;
import com.bsu.lab.constant.ConstantsForHouseCreating;

public class InputForFlatsCount {
    private final static int MAX_FLATS_COUNT = 20;
    private final static int MIN_FLATS_COUNT = 1;

    public static int input() {
        int flatsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
        while (flatsCount < MIN_FLATS_COUNT || flatsCount > MAX_FLATS_COUNT) {
            System.out.println("Введено неверное значение...Повторите ввод");
            flatsCount = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
        }
        return flatsCount;
    }
}
