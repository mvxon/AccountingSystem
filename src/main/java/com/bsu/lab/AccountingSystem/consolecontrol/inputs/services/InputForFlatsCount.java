package com.bsu.lab.AccountingSystem.consolecontrol.inputs.services;

import com.bsu.lab.AccountingSystem.consolecontrol.constants.ConstantsForHouseCreating;
import com.bsu.lab.AccountingSystem.consolecontrol.inputs.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputForFlatsCount {
    private final static int MAX_FLATS_COUNT = 20;
    private final static int MIN_FLATS_COUNT = 1;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForFlatsCount(SecuredNumbersScanner securedNumbersScanner) {
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public int input() {
        int flatsCount = securedNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
        while (flatsCount < MIN_FLATS_COUNT || flatsCount > MAX_FLATS_COUNT) {
            System.out.println("Введено неверное значение...Повторите ввод");
            flatsCount = securedNumbersScanner.enteringInfoCheck(ConstantsForHouseCreating.QUESTION_FOR_FLATS_PER_FLOOR);
        }
        return flatsCount;
    }
}
