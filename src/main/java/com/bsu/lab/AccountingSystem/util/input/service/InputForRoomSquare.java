package com.bsu.lab.AccountingSystem.util.input.service;

import com.bsu.lab.AccountingSystem.util.input.SecuredNumbersScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputForRoomSquare {
    private final static double MAX_SQUARE = 100;
    private final static double MIN_SQUARE = 1;
    private final SecuredNumbersScanner securedNumbersScanner;

    @Autowired
    public InputForRoomSquare(SecuredNumbersScanner securedNumbersScanner) {
        this.securedNumbersScanner = securedNumbersScanner;
    }

    public double input(int roomNumber) {
        String question = "Введите площадь " + roomNumber + "-ой комнаты(м^2)(от " + MIN_SQUARE + "м^2 до "
                + MAX_SQUARE + "м^2): ";
        double roomSquare = (securedNumbersScanner.enteringInfoCheck(question));

        while (roomSquare < MIN_SQUARE || roomSquare > MAX_SQUARE) {
            System.out.println("Введено неверное значение...Повторите ввод");
            roomSquare = (securedNumbersScanner.enteringInfoCheck(question));
        }
        return roomSquare;
    }
}
