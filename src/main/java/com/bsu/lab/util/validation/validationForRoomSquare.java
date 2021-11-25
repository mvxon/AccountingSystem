package com.bsu.lab.util.validation;

import com.bsu.lab.util.SecuredNumbersScanner;

public class validationForRoomSquare {
    private final static double MAX_SQUARE = 100;
    private final static double MIN_SQUARE = 1;

    public static double validate(int roomNumber) {
        String question = "Введите площадь " + roomNumber + "-ой комнаты(м^2)(от " + MIN_SQUARE + "м^2 до "
                + MAX_SQUARE + "м^2): ";
        double roomSquare = (SecuredNumbersScanner.EnteringInfoCheck(question));

        if (roomSquare < MIN_SQUARE || roomSquare > MAX_SQUARE) {
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                roomSquare = (SecuredNumbersScanner.EnteringInfoCheck(question));
            } while (roomSquare < MIN_SQUARE || roomSquare > MAX_SQUARE);
        }
        return roomSquare;
    }
}
