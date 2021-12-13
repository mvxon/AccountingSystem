package com.bsu.lab.util.input.service;

import com.bsu.lab.constant.ConstantsForHouseCreating;
import com.bsu.lab.util.input.SecuredNumbersScanner;

public class inputForHouseNumber {
    public static int input() {
        int choice = 0;
        int result = 0; // will be 0 if chosen auto input
        while (choice <= 0 || choice > 2) {
            choice = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.
                    QUESTION_FOR_INPUT_METHOD_OF_HOUSE_NUMBER);
            if (choice == 2) {
                while (result <= 0 || result > 100) {
                    result = SecuredNumbersScanner.EnteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_HOUSE_NUMBER);
                }
            }
            if (choice == 1) {
                return result;
            }
        }
        return result;
    }
}
