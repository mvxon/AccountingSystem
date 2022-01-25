package com.bsu.lab.util.input.consolecontrol.action;

import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.input.SecuredNumbersScanner;

public class InputForFlatNumber {
    public static int input(House house) {
        int flatNumber;
        String flatNumberQuestion;
        if (HouseService.getFlatsCount(house) != 1) {
            flatNumberQuestion = "Введите номер нужной квартиры(1-";
            flatNumberQuestion += HouseService.getFlatsCount(house) + "): ";
        } else {
            flatNumberQuestion = "Введите номер нужной квартиры(1): ";
        }
        flatNumber = SecuredNumbersScanner.EnteringInfoCheck(flatNumberQuestion);
        while (flatNumber > HouseService.getFlatsCount(house) || flatNumber <= 0) {

            System.out.println("Введен номер несуществующей квартиры...Повторите ввод");
            flatNumber = SecuredNumbersScanner.EnteringInfoCheck(flatNumberQuestion);
        }
        return flatNumber;
    }
}
