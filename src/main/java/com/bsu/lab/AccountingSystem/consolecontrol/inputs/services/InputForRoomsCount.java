package com.bsu.lab.AccountingSystem.consolecontrol.inputs.services;

import com.bsu.lab.AccountingSystem.consolecontrol.inputs.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputForRoomsCount {
    private final static int MAX_ROOMS_COUNT = 7;
    private final static int MIN_ROOMS_COUNT = 1;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForRoomsCount(SecuredNumbersScanner securedNumbersScanner) {
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public int input(int flatNumber) {
        String question = "Введите количество комнат " + flatNumber +
                "-ой квартиры на этаже(от " + MIN_ROOMS_COUNT + " до " + MAX_ROOMS_COUNT + "): ";
        int roomsCount = (securedNumbersScanner.enteringInfoCheck(question));
        while (roomsCount < MIN_ROOMS_COUNT || roomsCount > MAX_ROOMS_COUNT) {
                System.out.println("Введено неверное значение...Повторите ввод");
                roomsCount = (securedNumbersScanner.enteringInfoCheck(question));
        }
        return roomsCount;
    }
}
