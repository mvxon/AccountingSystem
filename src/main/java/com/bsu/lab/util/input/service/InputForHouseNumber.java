package com.bsu.lab.util.input.service;

import com.bsu.lab.constant.ConstantsForHouseCreating;
import com.bsu.lab.util.input.SecuredNumbersScanner;

public class InputForHouseNumber {
    public static int input() {
        int result = 0; // will be 0 if chosen auto input

        while (result <= 0 || result > 100) {
            result = SecuredNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_HOUSE_NUMBER);
        }
        return result;
    }
}
