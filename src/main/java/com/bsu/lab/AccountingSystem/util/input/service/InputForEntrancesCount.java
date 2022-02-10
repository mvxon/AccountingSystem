package com.bsu.lab.AccountingSystem.util.input.service;

import com.bsu.lab.AccountingSystem.constant.ConstantsForHouseCreating;
import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
public class InputForEntrancesCount {
    private final static int MAX_ENTRANCES_COUNT = 20;
    private final static int MIN_ENTRANCES_COUNT = 1;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForEntrancesCount(@Lazy SecuredNumbersScanner securedNumbersScanner) {
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public int input() {
        int entrancesCount = securedNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating
                .QUESTION_FOR_ENTRANCES_COUNT);
        while (entrancesCount < MIN_ENTRANCES_COUNT || entrancesCount > MAX_ENTRANCES_COUNT) {
            System.out.println(ConstantsForHouseCreating.INVALID_INPUT_ERROR);
            entrancesCount = securedNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating
                    .QUESTION_FOR_ENTRANCES_COUNT);
        }
        return entrancesCount;
    }
}
