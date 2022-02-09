package com.bsu.lab.AccountingSystem.util.input.service;

import com.bsu.lab.AccountingSystem.constant.ConstantsForHouseCreating;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class InputForFloorsCount {
    private final static int MAX_FLOORS_COUNT = 163;
    private final static int MIN_FLOORS_COUNT = 1;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForFloorsCount(@Lazy SecuredNumbersScanner securedNumbersScanner) {
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public int input() {
        int floorsCount = securedNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
        while (floorsCount < MIN_FLOORS_COUNT || floorsCount > MAX_FLOORS_COUNT) {
            System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
            floorsCount = securedNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLOORS_COUNT);
        }
        return floorsCount;
    }
}
