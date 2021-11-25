package com.bsu.lab.util.validation;

import com.bsu.lab.util.SecuredNumbersScanner;

public class validationForRoomsCount {
    private final static int MAX_ROOMS_COUNT = 7;
    private final static int MIN_ROOMS_COUNT = 1;

    public static int validate(int flatNumber) {
        String question = "Введите количество комнат " + flatNumber +
                "-ой квартиры на этаже(от " + MIN_ROOMS_COUNT + " до " + MAX_ROOMS_COUNT + "): ";
        int roomsCount = (SecuredNumbersScanner.EnteringInfoCheck(question));
        if (roomsCount < MIN_ROOMS_COUNT || roomsCount > MAX_ROOMS_COUNT) {
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                roomsCount = (SecuredNumbersScanner.EnteringInfoCheck(question));
            } while (roomsCount < MIN_ROOMS_COUNT || roomsCount > MAX_ROOMS_COUNT);
        }
        return roomsCount;
    }
}
