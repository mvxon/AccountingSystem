package com.bsu.lab.AccountingSystem.util.input.service;

import com.bsu.lab.AccountingSystem.constant.ConstantsForHouseCreating;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class InputForHouseNumber {
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForHouseNumber(@Lazy SecuredNumbersScanner securedNumbersScanner) {
        this.securedNumbersScanner = securedNumbersScanner;
    }
    public int input() {
        int result = 0; // will be 0 if chosen auto input

        while (result <= 0 || result > 100) {
            result = securedNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_HOUSE_NUMBER);
        }
        return result;
    }
}
