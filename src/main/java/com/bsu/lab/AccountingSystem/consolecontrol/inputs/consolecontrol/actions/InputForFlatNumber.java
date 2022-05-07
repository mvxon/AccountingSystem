package com.bsu.lab.AccountingSystem.consolecontrol.inputs.consolecontrol.actions;

import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.consolecontrol.inputs.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputForFlatNumber {
    private final HouseService houseService;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForFlatNumber(HouseService houseService, SecuredNumbersScanner securedNumbersScanner) {
        this.houseService = houseService;
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public int input(House house) {
        int flatNumber;
        String flatNumberQuestion;
        if (houseService.getFlatsCount(house) != 1) {
            flatNumberQuestion = "Введите номер нужной квартиры(1-";
            flatNumberQuestion += houseService.getFlatsCount(house) + "): ";
        } else {
            flatNumberQuestion = "Введите номер нужной квартиры(1): ";
        }
        flatNumber = securedNumbersScanner.enteringInfoCheck(flatNumberQuestion);
        while (flatNumber > houseService.getFlatsCount(house) || flatNumber <= 0) {

            System.out.println("Введен номер несуществующей квартиры...Повторите ввод");
            flatNumber = securedNumbersScanner.enteringInfoCheck(flatNumberQuestion);
        }
        return flatNumber;
    }
}
