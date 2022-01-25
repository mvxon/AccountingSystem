package com.bsu.lab.util.input.service;

import com.bsu.lab.util.input.SecuredNumbersScanner;

public class InputForRoomSquare {
    private final static double MAX_SQUARE = 100;
    private final static double MIN_SQUARE = 1;

    public static double input(int roomNumber) {
        String question = "Введите площадь " + roomNumber + "-ой комнаты(м^2)(от " + MIN_SQUARE + "м^2 до "
                + MAX_SQUARE + "м^2): ";
        double roomSquare = (SecuredNumbersScanner.EnteringInfoCheck(question));

        while (roomSquare < MIN_SQUARE || roomSquare > MAX_SQUARE) {
            System.out.println("Введено неверное значение...Повторите ввод");
            roomSquare = (SecuredNumbersScanner.EnteringInfoCheck(question));
        }
        return roomSquare;
    }
}
